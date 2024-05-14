@Test
	public void testExplicitJoining() throws Exception {
		assertFalse( JtaStatusHelper.isActive( TestingJtaPlatformImpl.INSTANCE.getTransactionManager() ) );

		SessionImplementor session = (SessionImplementor) sessionFactory().withOptions().autoJoinTransactions( false ).openSession();
		TransactionImplementor transaction = (TransactionImplementor) ( (Session) session ).getTransaction();

		assertFalse( session.getTransactionCoordinator().isSynchronizationRegistered() );
		assertFalse( transaction.isParticipating() );

		session.getFlushMode();  // causes a call to TransactionCoordinator#pulse

		assertFalse( session.getTransactionCoordinator().isSynchronizationRegistered() );
		assertFalse( transaction.isParticipating() );

		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().begin();

		assertTrue( JtaStatusHelper.isActive( TestingJtaPlatformImpl.INSTANCE.getTransactionManager() ) );
		assertTrue( transaction.isActive() );
		assertFalse( transaction.isParticipating() );
		assertFalse( session.getTransactionCoordinator().isSynchronizationRegistered() );

		session.getFlushMode();

		assertTrue( JtaStatusHelper.isActive( TestingJtaPlatformImpl.INSTANCE.getTransactionManager() ) );
		assertTrue( transaction.isActive() );
		assertFalse( session.getTransactionCoordinator().isSynchronizationRegistered() );
		assertFalse( transaction.isParticipating() );

		transaction.markForJoin();
		transaction.join();
		session.getFlushMode();

		assertTrue( JtaStatusHelper.isActive( TestingJtaPlatformImpl.INSTANCE.getTransactionManager() ) );
		assertTrue( transaction.isActive() );
		assertTrue( session.getTransactionCoordinator().isSynchronizationRegistered() );
		assertTrue( transaction.isParticipating() );

		( (Session) session ).close();

		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().commit();
	}