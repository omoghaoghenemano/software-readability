@Test
	public void testQueryCacheModes() {
		EntityManager em = getOrCreateEntityManager();
		Query jpaQuery = em.createQuery( "from SimpleEntity" );
		AbstractQueryImpl hibQuery = (AbstractQueryImpl) ( (HibernateQuery) jpaQuery ).getHibernateQuery();

		jpaQuery.setHint( AvailableSettings.SHARED_CACHE_STORE_MODE, CacheStoreMode.USE );
		assertEquals( CacheStoreMode.USE, jpaQuery.getHints().get( AvailableSettings.SHARED_CACHE_STORE_MODE ) );
		assertEquals( CacheMode.NORMAL, hibQuery.getCacheMode() );

		jpaQuery.setHint( AvailableSettings.SHARED_CACHE_STORE_MODE, CacheStoreMode.BYPASS );
		assertEquals( CacheStoreMode.BYPASS, jpaQuery.getHints().get( AvailableSettings.SHARED_CACHE_STORE_MODE ) );
		assertEquals( CacheMode.GET, hibQuery.getCacheMode() );

		jpaQuery.setHint( AvailableSettings.SHARED_CACHE_STORE_MODE, CacheStoreMode.REFRESH );
		assertEquals( CacheStoreMode.REFRESH, jpaQuery.getHints().get( AvailableSettings.SHARED_CACHE_STORE_MODE ) );
		assertEquals( CacheMode.REFRESH, hibQuery.getCacheMode() );

		jpaQuery.setHint( AvailableSettings.SHARED_CACHE_RETRIEVE_MODE, CacheRetrieveMode.BYPASS );
		assertEquals( CacheRetrieveMode.BYPASS, jpaQuery.getHints().get( AvailableSettings.SHARED_CACHE_RETRIEVE_MODE ) );
		assertEquals( CacheStoreMode.REFRESH, jpaQuery.getHints().get( AvailableSettings.SHARED_CACHE_STORE_MODE ) );
		assertEquals( CacheMode.REFRESH, hibQuery.getCacheMode() );

		jpaQuery.setHint( AvailableSettings.SHARED_CACHE_STORE_MODE, CacheStoreMode.BYPASS );
		assertEquals( CacheRetrieveMode.BYPASS, jpaQuery.getHints().get( AvailableSettings.SHARED_CACHE_RETRIEVE_MODE ) );
		assertEquals( CacheStoreMode.BYPASS, jpaQuery.getHints().get( AvailableSettings.SHARED_CACHE_STORE_MODE ) );
		assertEquals( CacheMode.IGNORE, hibQuery.getCacheMode() );

		jpaQuery.setHint( AvailableSettings.SHARED_CACHE_STORE_MODE, CacheStoreMode.USE );
		assertEquals( CacheRetrieveMode.BYPASS, jpaQuery.getHints().get( AvailableSettings.SHARED_CACHE_RETRIEVE_MODE ) );
		assertEquals( CacheStoreMode.USE, jpaQuery.getHints().get( AvailableSettings.SHARED_CACHE_STORE_MODE ) );
		assertEquals( CacheMode.PUT, hibQuery.getCacheMode() );
	}