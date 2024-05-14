private AnnotationInstance overrideSchemaCatalogByDefault(AnnotationInstance annotationInstance, EntityMappingsMocker.Default defaults) {
		List<AnnotationValue> newAnnotationValueList = new ArrayList<AnnotationValue>();
		newAnnotationValueList.addAll( annotationInstance.values() );
		boolean schemaDefined = false;
		boolean catalogDefined = false;
		if ( annotationInstance.value( "schema" ) != null ) {
			schemaDefined = true;
		}
		if ( annotationInstance.value( "catalog" ) != null ) {
			catalogDefined = true;
		}
		if ( schemaDefined && catalogDefined ) {
			return annotationInstance;
		}
		if ( !catalogDefined && StringHelper.isNotEmpty( defaults.getCatalog() ) ) {
			newAnnotationValueList.add(
					AnnotationValue.createStringValue(
							"catalog", defaults.getCatalog()
					)
			);
		}
		if ( !schemaDefined && StringHelper.isNotEmpty( defaults.getSchema() ) ) {
			newAnnotationValueList.add(
					AnnotationValue.createStringValue(
							"schema", defaults.getSchema()
					)
			);
		}
		return MockHelper.create(
				annotationInstance.name(),
				annotationInstance.target(),
				MockHelper.toArray( newAnnotationValueList )
		);
	}