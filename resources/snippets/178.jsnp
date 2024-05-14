@Test
    public void testExplicitPropertyAccessAnnotationsWithHibernateStyleOverride() throws Exception {
        AnnotationConfiguration cfg = new AnnotationConfiguration();
        Class<?> classUnderTest = Course3.class;
        cfg.addAnnotatedClass( classUnderTest );
        cfg.addAnnotatedClass( Student.class );
        SessionFactoryImplementor factory = (SessionFactoryImplementor) cfg.buildSessionFactory( serviceRegistry );
        EntityTuplizer tuplizer = factory.getEntityPersister( classUnderTest.getName() )
                .getEntityMetamodel()
                .getTuplizer();
        assertTrue(
                "Field access should be used.",
                tuplizer.getIdentifierGetter() instanceof DirectPropertyAccessor.DirectGetter
        );

        assertTrue(
                "Property access should be used.",
                tuplizer.getGetter( 0 ) instanceof BasicPropertyAccessor.BasicGetter
        );
		factory.close();
    }