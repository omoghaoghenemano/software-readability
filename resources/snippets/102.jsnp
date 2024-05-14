/**
	 * Throws {@link org.hibernate.PropertyValueException} if there are any unresolved
	 * entity insert actions that depend on non-nullable associations with
	 * a transient entity. This method should be called on completion of
	 * an operation (after all cascades are completed) that saves an entity.
	 *
	 * @throws org.hibernate.PropertyValueException if there are any unresolved entity
	 * insert actions; {@link org.hibernate.PropertyValueException#getEntityName()}
	 * and {@link org.hibernate.PropertyValueException#getPropertyName()} will
	 * return the entity name and property value for the first unresolved
	 * entity insert action.
	 */
	public void checkNoUnresolvedActionsAfterOperation() throws PropertyValueException {
		if ( isEmpty() ) {
			LOG.trace( "No entity insert actions have non-nullable, transient entity dependencies." );
		}
		else {
			final AbstractEntityInsertAction firstDependentAction =
					dependenciesByAction.keySet().iterator().next();

			logCannotResolveNonNullableTransientDependencies( firstDependentAction.getSession() );

			final NonNullableTransientDependencies nonNullableTransientDependencies =
					dependenciesByAction.get( firstDependentAction );
			final Object firstTransientDependency =
					nonNullableTransientDependencies.getNonNullableTransientEntities().iterator().next();
			final String firstPropertyPath =
					nonNullableTransientDependencies.getNonNullableTransientPropertyPaths( firstTransientDependency ).iterator().next();

			throw new TransientPropertyValueException(
					"Not-null property references a transient value - transient instance must be saved before current operation",
					firstDependentAction.getSession().guessEntityName( firstTransientDependency ),
					firstDependentAction.getEntityName(),
					firstPropertyPath
			);
		}
	}