private InfinispanRegionFactory createRegionFactory(final EmbeddedCacheManager manager, Properties p) {
      final InfinispanRegionFactory factory = new SingleNodeTestCase.TestInfinispanRegionFactory() {

         @Override
         protected org.infinispan.transaction.lookup.TransactionManagerLookup createTransactionManagerLookup(Settings settings, Properties properties) {
            return new HibernateTransactionManagerLookup(null, null) {
               @Override
               public TransactionManager getTransactionManager() throws Exception {
                  AbstractJtaPlatform jta = new JBossStandAloneJtaPlatform();
                  jta.injectServices(ServiceRegistryBuilder.buildServiceRegistry());
                  return jta.getTransactionManager();
               }
            };
         }

         @Override
         protected EmbeddedCacheManager createCacheManager(Properties properties) throws CacheException {
            if (manager != null)
               return manager;
            else
               return super.createCacheManager(properties);
         }

      };

      factory.start(null, p);
      return factory;
   }