public void redefineColumnName(String columnName, String propertyName, boolean applyNamingStrategy) {
		if ( applyNamingStrategy ) {
			if ( StringHelper.isEmpty( columnName ) ) {
				if ( propertyName != null ) {
					mappingColumn.setName(
							mappings.getObjectNameNormalizer().normalizeIdentifierQuoting(
									mappings.getNamingStrategy().propertyToColumnName( propertyName )
							)
					);
				}
				//Do nothing otherwise
			}
			else {
				columnName = mappings.getObjectNameNormalizer().normalizeIdentifierQuoting( columnName );
				columnName = mappings.getNamingStrategy().columnName( columnName );
				columnName = mappings.getObjectNameNormalizer().normalizeIdentifierQuoting( columnName );
				mappingColumn.setName( columnName );
			}
		}
		else {
			if ( StringHelper.isNotEmpty( columnName ) ) {
				mappingColumn.setName( mappings.getObjectNameNormalizer().normalizeIdentifierQuoting( columnName ) );
			}
		}
	}