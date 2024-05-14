/**
     * ...as the moon sets over the early morning Merlin, Oregon
     * mountains, our intrepid adventurers type...
     */
    static public Test createTest(Class<?> theClass, String name) {
        Constructor<?> constructor;
        try {
            constructor = getTestConstructor(theClass);
        } catch (NoSuchMethodException e) {
            return warning("Class " + theClass.getName() + " has no public constructor TestCase(String name) or TestCase()");
        }
        Object test;
        try {
            if (constructor.getParameterTypes().length == 0) {
                test = constructor.newInstance(new Object[0]);
                if (test instanceof TestCase) {
                    ((TestCase) test).setName(name);
                }
            } else {
                test = constructor.newInstance(new Object[]{name});
            }
        } catch (InstantiationException e) {
            return (warning("Cannot instantiate test case: " + name + " (" + exceptionToString(e) + ")"));
        } catch (InvocationTargetException e) {
            return (warning("Exception in constructor: " + name + " (" + exceptionToString(e.getTargetException()) + ")"));
        } catch (IllegalAccessException e) {
            return (warning("Cannot access test case: " + name + " (" + exceptionToString(e) + ")"));
        }
        return (Test) test;
    }