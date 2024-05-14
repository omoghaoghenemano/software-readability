@Test public void testOtherSyntax() throws Exception {
		parse( "select bar from org.hibernate.test.Bar bar order by ((bar.x - :valueX)*(bar.x - :valueX))" );
		parse( "from bar in class org.hibernate.test.Bar, foo in elements(bar.baz.fooSet)" );
		parse( "from one in class org.hibernate.test.One, many in elements(one.manies) where one.id = 1 and many.id = 1" );
		parse( "from org.hibernate.test.Inner _inner join _inner.middles middle" );
		parse( "FROM m IN CLASS org.hibernate.test.Master WHERE NOT EXISTS ( FROM d IN elements(m.details) WHERE NOT d.i=5 )" );
		parse( "FROM m IN CLASS org.hibernate.test.Master WHERE NOT 5 IN ( SELECT d.i FROM d IN elements(m.details) )" );
		parse( "SELECT m FROM m IN CLASS org.hibernate.test.Master, d IN elements(m.details) WHERE d.i=5" );
		parse( "SELECT m FROM m IN CLASS org.hibernate.test.Master, d IN elements(m.details) WHERE d.i=5" );
		parse( "SELECT m.id FROM m IN CLASS org.hibernate.test.Master, d IN elements(m.details) WHERE d.i=5" );
		// I'm not sure about these... [jsd]
//        parse("select bar.string, foo.string from bar in class org.hibernate.test.Bar inner join bar.baz as baz inner join elements(baz.fooSet) as foo where baz.name = 'name'");
//        parse("select bar.string, foo.string from bar in class org.hibernate.test.Bar, bar.baz as baz, elements(baz.fooSet) as foo where baz.name = 'name'");
//        parse("select count(*) where this.amount>-1 and this.name is null");
//        parse("from sm in class org.hibernate.test.SubMulti where exists sm.children.elements");
	}