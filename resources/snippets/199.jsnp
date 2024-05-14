@Test
	public void testChildIdColumnName() {
		Assert.assertEquals(
				"other_id",
				((Column) getCfg()
						.getClassMapping(
								"org.hibernate.envers.test.integration.inheritance.joined.primarykeyjoin.ChildPrimaryKeyJoinEntity_AUD"
						)
						.getKey().getColumnIterator().next()).getName()
		);
	}