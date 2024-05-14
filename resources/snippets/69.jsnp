@SkipForDialect(value = AbstractHANADialect.class, comment = "HANA currently requires specifying table name by 'FOR UPDATE of t1.c1' if there are more than one tables/views/subqueries in the FROM clause")
	@Test
	public void testRefresh() throws Exception {
		final Session s = openSession();
		s.beginTransaction();
		Foo foo = new Foo();
		s.save( foo );
		s.flush();
		s.doWork(
				new AbstractWork() {
					@Override
					public void execute(Connection connection) throws SQLException {
						final String sql = "update " + getDialect().openQuote() + "foos" + getDialect().closeQuote() + " set long_ = -3";
						Statement st = connection.createStatement();
						st.executeUpdate( sql );
					}
				}
		);
		s.refresh(foo);
		assertEquals( Long.valueOf( -3l ), foo.getLong() );
		assertEquals( LockMode.READ, s.getCurrentLockMode( foo ) );
		s.refresh(foo, LockMode.UPGRADE);
		if ( getDialect().supportsOuterJoinForUpdate() ) {
			assertEquals( LockMode.UPGRADE, s.getCurrentLockMode( foo ) );
		}
		s.delete(foo);
		s.getTransaction().commit();
		s.close();
	}