/**
     * Verifies that listeners added with addFirstListener() while tests are run concurrently are
     * notified about test failures.
     */
    @Test
    public void reportConcurrentFailuresAfterAddFirstListener() throws Exception {
        new AbstractConcurrentFailuresTest() {
            @Override
            protected void addListener(ExaminedListener listener) {
                fNotifier.addFirstListener(listener);
            }
        }.test();
    }