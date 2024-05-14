@Test
	public void testFetchInSubqueryFails() {
		Session s = openSession();
		try {
			s.createQuery( "from Animal a where a.mother in (select m from Animal a1 inner join a1.mother as m join fetch m.mother)" ).list();
			fail( "fetch join allowed in subquery" );
		}
		catch( QueryException expected ) {
			// expected behavior
		}
		s.close();
	}