/**
	 * Execute given <tt>PreparedStatement</tt>, advance to the first result and return SQL <tt>ResultSet</tt>.
	 */
	protected final ResultSet getResultSet(
			final PreparedStatement st,
			final RowSelection selection,
			final LimitHandler limitHandler,
			final boolean autodiscovertypes,
			final SessionImplementor session)
	throws SQLException, HibernateException {

		try {
			ResultSet rs = session.getTransactionCoordinator().getJdbcCoordinator().getResultSetReturn().extract( st );
			rs = wrapResultSetIfEnabled( rs , session );

			if ( !limitHandler.supportsLimitOffset() || !LimitHelper.useLimit( limitHandler, selection ) ) {
				advance( rs, selection );
			}

			if ( autodiscovertypes ) {
				autoDiscoverTypes( rs );
			}
			return rs;
		}
		catch ( SQLException sqle ) {
			session.getTransactionCoordinator().getJdbcCoordinator().release( st );
			throw sqle;
		}
	}