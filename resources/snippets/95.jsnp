private void releaseStatement(Session session, PreparedStatement ps) {
		if ( ps != null ) {
			try {
				( (SessionImplementor) session ).getTransactionCoordinator().getJdbcCoordinator().release( ps );
			}
			catch ( Throwable ignore ) {
				// ignore...
			}
		}
	}