@Test
   public void testBuildEntityRegionPersonPlusEntityOverridesWithoutCfg() {
      final String person = "com.acme.Person";
      Properties p = new Properties();
      // Third option, no cache defined for entity and overrides for generic entity data type and entity itself.
      p.setProperty("hibernate.cache.infinispan.com.acme.Person.eviction.strategy", "LRU");
      p.setProperty("hibernate.cache.infinispan.com.acme.Person.expiration.lifespan", "60000");
      p.setProperty("hibernate.cache.infinispan.com.acme.Person.expiration.max_idle", "30000");
      p.setProperty("hibernate.cache.infinispan.entity.cfg", "myentity-cache");
      p.setProperty("hibernate.cache.infinispan.entity.eviction.strategy", "FIFO");
      p.setProperty("hibernate.cache.infinispan.entity.eviction.wake_up_interval", "3000");
      p.setProperty("hibernate.cache.infinispan.entity.eviction.max_entries", "10000");
      InfinispanRegionFactory factory = createRegionFactory(p);
      try {
         factory.getCacheManager();
         assertNotNull(factory.getTypeOverrides().get(person));
         assertFalse(factory.getDefinedConfigurations().contains(person));
         EntityRegionImpl region = (EntityRegionImpl) factory.buildEntityRegion(person, p, null);
         assertNotNull(factory.getTypeOverrides().get(person));
         assertTrue(factory.getDefinedConfigurations().contains(person));
         AdvancedCache cache = region.getCache();
         Configuration cacheCfg = cache.getCacheConfiguration();
         assertEquals(EvictionStrategy.LRU, cacheCfg.eviction().strategy());
         assertEquals(3000, cacheCfg.expiration().wakeUpInterval());
         assertEquals(10000, cacheCfg.eviction().maxEntries());
         assertEquals(60000, cacheCfg.expiration().lifespan());
         assertEquals(30000, cacheCfg.expiration().maxIdle());
      } finally {
         factory.stop();
      }
   }