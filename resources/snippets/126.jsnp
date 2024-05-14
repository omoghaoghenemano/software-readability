/**
	 * Build the metamodel using the information from the collection of Hibernate
	 * {@link PersistentClass} models as well as the Hibernate {@link org.hibernate.SessionFactory}.
	 *
	 * @param persistentClasses Iterator over the Hibernate (config-time) metamodel
	 * @param mappedSuperclasses All known MappedSuperclasses
	 * @param sessionFactory The Hibernate session factory.
     * @param ignoreUnsupported ignore unsupported/unknown annotations (like @Any)
	 *
	 * @return The built metamodel
	 */
	public static MetamodelImpl buildMetamodel(
			Iterator<PersistentClass> persistentClasses,
			Set<MappedSuperclass> mappedSuperclasses,
			SessionFactoryImplementor sessionFactory,
            boolean ignoreUnsupported) {
		MetadataContext context = new MetadataContext( sessionFactory, mappedSuperclasses, ignoreUnsupported );
		while ( persistentClasses.hasNext() ) {
			PersistentClass pc = persistentClasses.next();
			locateOrBuildEntityType( pc, context );
		}
		handleUnusedMappedSuperclasses( context );
		context.wrapUp();
		return new MetamodelImpl( context.getEntityTypeMap(), context.getEmbeddableTypeMap(), context.getMappedSuperclassTypeMap(), context.getEntityTypesByEntityName() );
	}