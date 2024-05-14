private void resolveEntityKey(
			ResultSet resultSet,
			ResultSetProcessingContextImpl context,
			FetchSource fetchSource,
			Map<EntityReference,EntityReferenceInitializer> initializerByEntityReference) throws SQLException {
		// Resolve any bidirectional entity references first.
		for ( BidirectionalEntityReference bidirectionalEntityReference : fetchSource.getBidirectionalEntityReferences() ) {
			final EntityReferenceInitializer targetEntityReferenceInitializer = initializerByEntityReference.get(
					bidirectionalEntityReference.getTargetEntityReference()
			);
			resolveEntityKey(
					resultSet,
					context,
					targetEntityReferenceInitializer,
					initializerByEntityReference
			);
			targetEntityReferenceInitializer.hydrateEntityState( resultSet, context );
		}
		for ( Fetch fetch : fetchSource.getFetches() ) {
			if ( EntityFetch.class.isInstance( fetch ) ) {
				final EntityFetch entityFetch = (EntityFetch) fetch;
				final EntityReferenceInitializer  entityReferenceInitializer = initializerByEntityReference.get( entityFetch );
				if ( entityReferenceInitializer != null ) {
					resolveEntityKey(
							resultSet,
							context,
							entityReferenceInitializer,
							initializerByEntityReference
					);
					entityReferenceInitializer.hydrateEntityState( resultSet, context );
				}
			}
			else if ( CompositeFetch.class.isInstance( fetch ) ) {
				resolveEntityKey(
						resultSet,
						context,
						(CompositeFetch) fetch,
						initializerByEntityReference );
			}
		}
	}