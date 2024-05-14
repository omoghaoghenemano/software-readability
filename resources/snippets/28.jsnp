private static Ejb3DiscriminatorColumn processDiscriminatorProperties(XClass clazzToProcess, Mappings mappings, InheritanceState inheritanceState, EntityBinder entityBinder) {
		Ejb3DiscriminatorColumn discriminatorColumn = null;
		javax.persistence.DiscriminatorColumn discAnn = clazzToProcess.getAnnotation(
				javax.persistence.DiscriminatorColumn.class
		);
		DiscriminatorType discriminatorType = discAnn != null ?
				discAnn.discriminatorType() :
				DiscriminatorType.STRING;

		org.hibernate.annotations.DiscriminatorFormula discFormulaAnn = clazzToProcess.getAnnotation(
				org.hibernate.annotations.DiscriminatorFormula.class
		);
		if ( !inheritanceState.hasParents() ) {
			discriminatorColumn = Ejb3DiscriminatorColumn.buildDiscriminatorColumn(
					discriminatorType, discAnn, discFormulaAnn, mappings
			);
		}
		if ( discAnn != null && inheritanceState.hasParents() ) {
			LOG.invalidDiscriminatorAnnotation( clazzToProcess.getName() );
		}

		String discrimValue = clazzToProcess.isAnnotationPresent( DiscriminatorValue.class ) ?
				clazzToProcess.getAnnotation( DiscriminatorValue.class ).value() :
				null;
		entityBinder.setDiscriminatorValue( discrimValue );

		DiscriminatorOptions discriminatorOptions = clazzToProcess.getAnnotation( DiscriminatorOptions.class );
		if ( discriminatorOptions != null) {
			entityBinder.setForceDiscriminator( discriminatorOptions.force() );
			entityBinder.setInsertableDiscriminator( discriminatorOptions.insert() );
		}

		return discriminatorColumn;
	}