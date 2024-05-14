@Test
	public void testWithEJB3NamingStrategy() throws Exception {
		SessionFactory  sf = null;
		try {
			AnnotationConfiguration config = new AnnotationConfiguration();
			config.setNamingStrategy(EJB3NamingStrategy.INSTANCE);
			config.addAnnotatedClass(A.class);
			config.addAnnotatedClass(AddressEntry.class);
			sf = config.buildSessionFactory( serviceRegistry );
			Mappings mappings = config.createMappings();
			boolean foundIt = false;

			for ( Iterator iter = mappings.iterateTables(); iter.hasNext();  ) {
				Table table = (Table) iter.next();
                log.info("testWithEJB3NamingStrategy table = " + table.getName());
				if ( table.getName().equalsIgnoreCase("A_ADDRESS")) {
					foundIt = true;
				}
				// make sure we use A_ADDRESS instead of AEC_address
				assertFalse("got table name mapped to: AEC_address (should be A_ADDRESS) which violates JPA-2 spec section 11.1.8 ([OWNING_ENTITY_NAME]_[COLLECTION_ATTRIBUTE_NAME])",table.getName().equalsIgnoreCase("AEC_address"));
			}
			assertTrue("table not mapped to A_ADDRESS which violates JPA-2 spec section 11.1.8",foundIt);
		}
		catch( Exception e ) {
			StringWriter writer = new StringWriter();
			e.printStackTrace(new PrintWriter(writer));
            log.debug(writer.toString());
			fail(e.getMessage());
		} finally {
			if( sf != null ){
				sf.close();
			}
		}
	}