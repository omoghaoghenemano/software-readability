public EntityKey interpretEntityKey(
			SessionImplementor session,
			String optionalEntityName,
			Serializable optionalId,
			Object optionalObject) {
		if ( optionalEntityName != null ) {
			final EntityPersister entityPersister;
			if ( optionalObject != null ) {
				entityPersister = session.getEntityPersister( optionalEntityName, optionalObject );
			}
			else {
				entityPersister = session.getFactory().getEntityPersister( optionalEntityName );
			}
			if ( entityPersister.isInstance( optionalId ) &&
					!entityPersister.getEntityMetamodel().getIdentifierProperty().isVirtual() &&
					entityPersister.getEntityMetamodel().getIdentifierProperty().isEmbedded() ) {
				// non-encapsulated composite identifier
				final Serializable identifierState = ((CompositeType) entityPersister.getIdentifierType()).getPropertyValues(
						optionalId,
						session
				);
				return session.generateEntityKey( identifierState, entityPersister );
			}
			else {
				return session.generateEntityKey( optionalId, entityPersister );
			}
		}
		else {
			return null;
		}
	}