@Override
    public void runTest(final Test test, final TestResult result) {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    // inlined due to limitation in VA/Java
                    //ActiveTestSuite.super.runTest(test, result);
                    test.run(result);
                } finally {
                    ActiveTestSuite.this.runFinished();
                }
            }
        };
        t.start();
    }
