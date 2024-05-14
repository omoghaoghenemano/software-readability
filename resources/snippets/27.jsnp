private void considerExplicitFieldAndPropertyAccess() {
		for ( XProperty property : fieldAccessMap.values() ) {
			Access access = property.getAnnotation( Access.class );
			if ( access == null ) {
				continue;
			}

			// see "2.3.2 Explicit Access Type" of JPA 2 spec
			// the access type for this property is explicitly set to AccessType.FIELD, hence we have to
			// use field access for this property even if the default access type for the class is AccessType.PROPERTY
			AccessType accessType = AccessType.getAccessStrategy( access.value() );
            if (accessType == AccessType.FIELD) {
				propertyAccessMap.put(property.getName(), property);
			}
            else {
				LOG.debug( "Placing @Access(AccessType.FIELD) on a field does not have any effect." );
			}
		}

		for ( XProperty property : propertyAccessMap.values() ) {
			Access access = property.getAnnotation( Access.class );
			if ( access == null ) {
				continue;
			}

			AccessType accessType = AccessType.getAccessStrategy( access.value() );

			// see "2.3.2 Explicit Access Type" of JPA 2 spec
			// the access type for this property is explicitly set to AccessType.PROPERTY, hence we have to
			// return use method access even if the default class access type is AccessType.FIELD
            if (accessType == AccessType.PROPERTY) {
				fieldAccessMap.put(property.getName(), property);
			}
            else {
				LOG.debug( "Placing @Access(AccessType.PROPERTY) on a field does not have any effect." );
			}
		}
	}