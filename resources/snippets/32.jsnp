/**
     * Retrieves a PropertyAccessor instance based on the given property definition and
     * entity mode.
     *
     * @param property The property for which to retrieve an accessor.
     * @param mode The mode for the resulting entity.
     * @return An appropriate accessor.
     * @throws MappingException
     */
	public static PropertyAccessor getPropertyAccessor(AttributeBinding property, EntityMode mode) throws MappingException {
		//TODO: this is temporary in that the end result will probably not take a Property reference per-se.
	    if ( null == mode || EntityMode.POJO.equals( mode ) ) {
		    return getPojoPropertyAccessor( property.getPropertyAccessorName() );
	    }
	    else if ( EntityMode.MAP.equals( mode ) ) {
		    return getDynamicMapPropertyAccessor();
	    }
	    else {
		    throw new MappingException( "Unknown entity mode [" + mode + "]" );
	    }
	}