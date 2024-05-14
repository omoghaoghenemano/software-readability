/**
	 * Test default optimizer selection for sequence backed generators
	 * based on the configured increment size; both in the case of the
	 * dialect supporting pooled sequences (pooled) and not (hilo)
	 */
	@Test
	public void testDefaultOptimizerBasedOnIncrementBackedBySequence() {
		Properties props = buildGeneratorPropertiesBase();
		props.setProperty( SequenceStyleGenerator.INCREMENT_PARAM, "10" );

		// for dialects which do not support pooled sequences, we default to pooled+table
		Dialect dialect = new SequenceDialect();
		SequenceStyleGenerator generator = new SequenceStyleGenerator();
		generator.configure( StandardBasicTypes.LONG, props, dialect );
		assertClassAssignability( TableStructure.class, generator.getDatabaseStructure().getClass() );
		assertClassAssignability( PooledOptimizer.class, generator.getOptimizer().getClass() );
		assertEquals( SequenceStyleGenerator.DEF_SEQUENCE_NAME, generator.getDatabaseStructure().getName() );

		// for dialects which do support pooled sequences, we default to pooled+sequence
		dialect = new PooledSequenceDialect();
		generator = new SequenceStyleGenerator();
		generator.configure( StandardBasicTypes.LONG, props, dialect );
		assertClassAssignability( SequenceStructure.class, generator.getDatabaseStructure().getClass() );
		assertClassAssignability( PooledOptimizer.class, generator.getOptimizer().getClass() );
		assertEquals( SequenceStyleGenerator.DEF_SEQUENCE_NAME, generator.getDatabaseStructure().getName() );
	}