private void registeredPutWithInterveningRemovalTest(
         final boolean transactional, final boolean removeRegion)
         throws Exception {
      withCacheManager(new CacheManagerCallable(
            TestCacheManagerFactory.createCacheManager(false)) {
         @Override
         public void call() {
            PutFromLoadValidator testee = new PutFromLoadValidator(cm,
                  transactional ? tm : null,
                  PutFromLoadValidator.NAKED_PUT_INVALIDATION_PERIOD);
            try {
               if (transactional) {
                  tm.begin();
               }
               testee.registerPendingPut(KEY1);
               if (removeRegion) {
                  testee.invalidateRegion();
               } else {
                  testee.invalidateKey(KEY1);
               }

               boolean lockable = testee.acquirePutFromLoadLock(KEY1);
               try {
                  assertFalse(lockable);
               }
               finally {
                  if (lockable) {
                     testee.releasePutFromLoadLock(KEY1);
                  }
               }
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         }
      });
   }