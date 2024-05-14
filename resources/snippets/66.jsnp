/**
	 * 1. Recreate the collection key -> collection map
	 * 2. rebuild the collection entries
	 * 3. call Interceptor.postFlush()
	 */
	protected void postFlush(SessionImplementor session) throws HibernateException {

		LOG.trace( "Post flush" );

		final PersistenceContext persistenceContext = session.getPersistenceContext();
		persistenceContext.getCollectionsByKey().clear();
		
		// the database has changed now, so the subselect results need to be invalidated
		// the batch fetching queues should also be cleared - especially the collection batch fetching one
		persistenceContext.getBatchFetchQueue().clear();

		for ( Map.Entry<PersistentCollection, CollectionEntry> me : IdentityMap.concurrentEntries( persistenceContext.getCollectionEntries() ) ) {
			CollectionEntry collectionEntry = me.getValue();
			PersistentCollection persistentCollection = me.getKey();
			collectionEntry.postFlush(persistentCollection);
			if ( collectionEntry.getLoadedPersister() == null ) {
				//if the collection is dereferenced, remove from the session cache
				//iter.remove(); //does not work, since the entrySet is not backed by the set
				persistenceContext.getCollectionEntries()
						.remove(persistentCollection);
			}
			else {
				//otherwise recreate the mapping between the collection and its key
				CollectionKey collectionKey = new CollectionKey(
						collectionEntry.getLoadedPersister(),
						collectionEntry.getLoadedKey()
				);
				persistenceContext.getCollectionsByKey().put(collectionKey, persistentCollection);
			}
		}

	}