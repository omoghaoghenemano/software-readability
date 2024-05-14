@Test
    public void listenersAreCalledCorrectlyInTheFaceOfFailures()
            throws Exception {
        JUnitCore core = new JUnitCore();
        final List<Failure> failures = new ArrayList<Failure>();
        core.addListener(new RunListener() {
            @Override
            public void testRunFinished(Result result) throws Exception {
                failures.addAll(result.getFailures());
            }
        });
        fMax.run(Request.aClass(TwoTests.class), core);
        assertEquals(1, failures.size());
    }