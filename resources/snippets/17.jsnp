@Test
	public void testHiLoAlgorithm() {
		session = (SessionImpl) sessionFactory.openSession();
		((Session)session).beginTransaction();

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// initially sequence should be uninitialized
		assertEquals( 0L, extractSequenceValue( (session) ) );

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// historically the hilo generators skipped the initial block of values;
		// 		so the first generated id value is maxlo + 1, here be 4
		Long generatedValue = (Long) generator.generate( session, null );
		assertEquals( 1L, generatedValue.longValue() );
		// which should also perform the first read on the sequence which should set it to its "start with" value (1)
		assertEquals( 1L, extractSequenceValue( (session) ) );

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		generatedValue = (Long) generator.generate( session, null );
		assertEquals( 2L, generatedValue.longValue() );
		assertEquals( 2L, extractSequenceValue( (session) ) );

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		generatedValue = (Long) generator.generate( session, null );
		assertEquals( 3L, generatedValue.longValue() );
		assertEquals( 3L, extractSequenceValue( (session) ) );

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		generatedValue = (Long) generator.generate( session, null );
		assertEquals( 4L, generatedValue.longValue() );
		assertEquals( 4L, extractSequenceValue( (session) ) );

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		generatedValue = (Long) generator.generate( session, null );
		assertEquals( 5L, generatedValue.longValue() );
		assertEquals( 5L, extractSequenceValue( (session) ) );

		((Session)session).getTransaction().commit();
		((Session)session).close();
	}