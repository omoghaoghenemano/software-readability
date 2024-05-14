private static Description makeDescription(Test test) {
        if (test instanceof TestCase) {
            TestCase tc = (TestCase) test;
            return Description.createTestDescription(tc.getClass(), tc.getName(),
                    getAnnotations(tc));
        } else if (test instanceof TestSuite) {
            TestSuite ts = (TestSuite) test;
            String name = ts.getName() == null ? createSuiteDescription(ts) : ts.getName();
            Description description = Description.createSuiteDescription(name);
            int n = ts.testCount();
            for (int i = 0; i < n; i++) {
                Description made = makeDescription(ts.testAt(i));
                description.addChild(made);
            }
            return description;
        } else if (test instanceof Describable) {
            Describable adapter = (Describable) test;
            return adapter.getDescription();
        } else if (test instanceof TestDecorator) {
            TestDecorator decorator = (TestDecorator) test;
            return makeDescription(decorator.getTest());
        } else {
            // This is the best we can do in this case
            return Description.createSuiteDescription(test.getClass());
        }
    }