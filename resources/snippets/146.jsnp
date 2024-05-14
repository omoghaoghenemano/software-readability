private void doBasicPluralAttributeBinding(PluralAttributeSource source, AbstractPluralAttributeBinding binding) {
		binding.setFetchTiming( source.getFetchTiming() );
		binding.setFetchStyle( source.getFetchStyle() );
		binding.setCascadeStyles( source.getCascadeStyles() );

		binding.setCaching( source.getCaching() );

		binding.getHibernateTypeDescriptor().setJavaTypeName(
				source.getPluralAttributeNature().reportedJavaType().getName()
		);
		binding.getHibernateTypeDescriptor().setExplicitTypeName( source.getTypeInformation().getName() );
		binding.getHibernateTypeDescriptor().getTypeParameters().putAll( source.getTypeInformation().getParameters() );

		if ( StringHelper.isNotEmpty( source.getCustomPersisterClassName() ) ) {
			binding.setCollectionPersisterClass(
					currentBindingContext.<CollectionPersister>locateClassByName( source.getCustomPersisterClassName() )
			);
		}

		if ( source.getCustomPersisterClassName() != null ) {
			binding.setCollectionPersisterClass(
					metadata.<CollectionPersister>locateClassByName( source.getCustomPersisterClassName() )
			);
		}

		binding.setCustomLoaderName( source.getCustomLoaderName() );
		binding.setCustomSqlInsert( source.getCustomSqlInsert() );
		binding.setCustomSqlUpdate( source.getCustomSqlUpdate() );
		binding.setCustomSqlDelete( source.getCustomSqlDelete() );
		binding.setCustomSqlDeleteAll( source.getCustomSqlDeleteAll() );

		binding.setMetaAttributeContext(
				buildMetaAttributeContext(
						source.metaAttributes(),
						binding.getContainer().getMetaAttributeContext()
				)
		);

		doBasicAttributeBinding( source, binding );
	}