public void testFailure() {
        String expected = expected(new String[]{".F", "Time: 0", "Failures here", "", "FAILURES!!!", "Tests run: 1,  Failures: 1,  Errors: 0", ""});
        ResultPrinter printer = new TestResultPrinter(new PrintStream(output)) {
            @Override
            public void printFailures(TestResult result) {
                getWriter().println("Failures here");
            }
        };
        runner.setPrinter(printer);
        TestSuite suite = new TestSuite();
        suite.addTest(new TestCase() {
            @Override
            public void runTest() {
                throw new AssertionFailedError();
            }
        });
        runner.doRun(suite);
        assertEquals(expected, output.toString());
    }