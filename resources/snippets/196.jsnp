public AbstractRowReader(ReaderCollector readerCollector) {
		this.entityReferenceInitializers = readerCollector.getEntityReferenceInitializers() != null
				? new ArrayList<EntityReferenceInitializer>( readerCollector.getEntityReferenceInitializers() )
				: Collections.<EntityReferenceInitializer>emptyList();
		this.arrayReferenceInitializers = readerCollector.getArrayReferenceInitializers() != null
				? new ArrayList<CollectionReferenceInitializer>( readerCollector.getArrayReferenceInitializers() )
				: Collections.<CollectionReferenceInitializer>emptyList();
		this.collectionReferenceInitializers = readerCollector.getNonArrayCollectionReferenceInitializers() != null
				? new ArrayList<CollectionReferenceInitializer>( readerCollector.getNonArrayCollectionReferenceInitializers() )
				: Collections.<CollectionReferenceInitializer>emptyList();
	}