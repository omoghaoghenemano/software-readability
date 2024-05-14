@Override
      public String call() throws Exception {
         try {
            if (isTrace)
               log.tracef("[%s] Wait for all executions paths to be ready to perform calls", title(warmup));
            barrier.await();

            long start = System.nanoTime();
            int runs = 0;
            if (isTrace)
               log.tracef("[%s] Start time: %d", title(warmup), start);

//            while (USE_TIME && PutFromLoadStressTestCase.this.run.get()) {
//               if (runs % 100000 == 0)
//                  log.infof("[%s] Query run # %d", title(warmup), runs);
//
////               Customer customer = query();
////               deleteCached(customer);

               queryItems();
//               deleteCachedItems();
//
//               runs++;
//            }
            long end = System.nanoTime();
            long duration = end - start;
            if (isTrace)
               log.tracef("[%s] End time: %d, duration: %d, runs: %d",
                     title(warmup), start, duration, runs);

            return opsPerMS(duration, runs);
         } finally {
            if (isTrace)
               log.tracef("[%s] Wait for all execution paths to finish", title(warmup));

            barrier.await();
         }
      }