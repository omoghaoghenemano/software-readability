@SuppressWarnings( {"SimplifiableIfStatement"})
	private boolean isUnequivocallyNonDirty(Object entity) {

		if(entity instanceof SelfDirtinessTracker)
			return ((SelfDirtinessTracker) entity).$$_hibernate_hasDirtyAttributes();

		final CustomEntityDirtinessStrategy customEntityDirtinessStrategy =
				persistenceContext.getSession().getFactory().getCustomEntityDirtinessStrategy();
		if ( customEntityDirtinessStrategy.canDirtyCheck( entity, getPersister(), (Session) persistenceContext.getSession() ) ) {
			return ! customEntityDirtinessStrategy.isDirty( entity, getPersister(), (Session) persistenceContext.getSession() );
		}

		if ( getPersister().hasMutableProperties() ) {
			return false;
		}

		if ( getPersister().getInstrumentationMetadata().isInstrumented() ) {
			// the entity must be instrumented (otherwise we cant check dirty flag) and the dirty flag is false
			return ! getPersister().getInstrumentationMetadata().extractInterceptor( entity ).isDirty();
		}

		return false;
	}