@Test public void testPathologicalKeywordAsIdentifier() throws Exception {
		// Super evil badness... a legitimate keyword!
		parse( "from Order order" );
		//parse( "from Order order join order.group" );
		parse( "from X x order by x.group.by.from" );
		parse( "from Order x order by x.order.group.by.from" );
		parse( "select order.id from Order order" );
		parse( "select order from Order order" );
		parse( "from Order order where order.group.by.from is not null" );
		parse( "from Order order order by order.group.by.from" );
		// Okay, now this is getting silly.
		parse( "from Group as group group by group.by.from" );
	}