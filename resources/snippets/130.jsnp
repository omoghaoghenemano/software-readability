@Test
   public void testBuildEntityCollectionRegionOverridesOnly() {
      AdvancedCache cache;
      Properties p = new Properties();
      p.setProperty("hibernate.cache.infinispan.entity.eviction.strategy", "LIRS");
      p.setProperty("hibernate.cache.infinispan.entity.eviction.wake_up_interval", "3000");
      p.setProperty("hibernate.cache.infinispan.entity.eviction.max_entries", "30000");
      p.setProperty("hibernate.cache.infinispan.collection.eviction.strategy", "LRU");
      p.setProperty("hibernate.cache.infinispan.collection.eviction.wake_up_interval", "3500");
      p.setProperty("hibernate.cache.infinispan.collection.eviction.max_entries", "35000");
      InfinispanRegionFactory factory = createRegionFactory(p);
      try {
         factory.getCacheManager();
         EntityRegionImpl region = (EntityRegionImpl) factory.buildEntityRegion("com.acme.Address", p, null);
         assertNull(factory.getTypeOverrides().get("com.acme.Address"));
         cache = region.getCache();
         Configuration cacheCfg = cache.getCacheConfiguration();
         assertEquals(EvictionStrategy.LIRS, cacheCfg.eviction().strategy());
         assertEquals(3000, cacheCfg.expiration().wakeUpInterval());
         assertEquals(30000, cacheCfg.eviction().maxEntries());
         // Max idle value comes from base XML configuration
         assertEquals(100000, cacheCfg.expiration().maxIdle());

         CollectionRegionImpl collectionRegion = (CollectionRegionImpl)
               factory.buildCollectionRegion("com.acme.Person.addresses", p, null);
         assertNull(factory.getTypeOverrides().get("com.acme.Person.addresses"));
         cache = collectionRegion.getCache();
         cacheCfg = cache.getCacheConfiguration();
         assertEquals(EvictionStrategy.LRU, cacheCfg.eviction().strategy());
         assertEquals(3500, cacheCfg.expiration().wakeUpInterval());
         assertEquals(35000, cacheCfg.eviction().maxEntries());
         assertEquals(100000, cacheCfg.expiration().maxIdle());
      } finally {
         factory.stop();
      }
   }