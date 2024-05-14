private void pushHibernateTypeInformationDownIfNeeded(
			HibernateTypeDescriptor hibernateTypeDescriptor,
			Value value,
			Type resolvedHibernateType) {
		if ( resolvedHibernateType == null ) {
			return;
		}
		if ( hibernateTypeDescriptor.getResolvedTypeMapping() == null ) {
			hibernateTypeDescriptor.setResolvedTypeMapping( resolvedHibernateType );
		}

		// java type information ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		if ( hibernateTypeDescriptor.getJavaTypeName() == null ) {
			hibernateTypeDescriptor.setJavaTypeName( resolvedHibernateType.getReturnedClass().getName() );
		}

	   // todo : this can be made a lot smarter, but for now this will suffice.  currently we only handle single value bindings

	   if ( SimpleValue.class.isInstance( value ) ) {
		   SimpleValue simpleValue = ( SimpleValue ) value;
		   if ( simpleValue.getDatatype() == null ) {
			   simpleValue.setDatatype(
					   new Datatype(
							   resolvedHibernateType.sqlTypes( metadata )[0],
							   resolvedHibernateType.getName(),
							   resolvedHibernateType.getReturnedClass()
					   )
			   );
		   }
	   }
	}