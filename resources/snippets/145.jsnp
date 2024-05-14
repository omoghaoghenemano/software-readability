@Test
	public void testExceptionHandling() {
		Session session = openSession();
		SessionImplementor sessionImpl = (SessionImplementor) session;
		boolean caught = false;
		try {
			PreparedStatement ps = sessionImpl.getTransactionCoordinator().getJdbcCoordinator().getStatementPreparer()
					.prepareStatement( "select count(*) from NON_EXISTENT" );
			sessionImpl.getTransactionCoordinator().getJdbcCoordinator().getResultSetReturn().execute( ps );
		}
		catch ( JDBCException ok ) {
			caught = true;
		}
		finally {
			session.close();
		}

		assertTrue( "The connection did not throw a JDBCException as expected", caught );
	}