@Test
	@TestForIssue( jiraKey = "HHH-21" )
    public void testCompositeIdId() throws HibernateException, SQLException {
        Session s = openSession();
		s.beginTransaction();
        CompositeIdId id = new CompositeIdId();
        id.setName("Max");
        id.setSystem("c64");
        id.setId("games");
        s.save(id);
		s.getTransaction().commit();
        s.close();

        s = openSession();
		s.beginTransaction();
        // having a composite id with one property named id works since the map used by sqlloader to map names to properties handles it.
		String sql = "select system as {c.system}, id as {c.id}, name as {c.name}, foo as {c.composite.foo}, bar as {c.composite.bar} from CompositeIdId where system=? and id=?";
		SQLQuery query = s.createSQLQuery( sql ).addEntity( "c", CompositeIdId.class );
        query.setString(0, "c64");
        query.setString(1, "games");

        CompositeIdId id2 = (CompositeIdId) query.uniqueResult();
        check(id, id2);

		s.getTransaction().commit();
        s.close();

        s = openSession();
		s.beginTransaction();
        CompositeIdId useForGet = new CompositeIdId();
        useForGet.setSystem("c64");
        useForGet.setId("games");
        // this doesn't work since the verification does not take column span into respect!
        CompositeIdId getted = (CompositeIdId) s.get(CompositeIdId.class, useForGet);
        check(id,getted);
		s.getTransaction().commit();
        s.close();
    }