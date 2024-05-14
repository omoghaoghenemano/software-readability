@Test
    public void stackTraceContainsRealCauseOfTimeout() throws Throwable {
        StuckStatement stuck = new StuckStatement();
        FailOnTimeout stuckTimeout = new FailOnTimeout(stuck, TIMEOUT);
        try {
            stuckTimeout.evaluate();
            // We must not get here, we expect a timeout exception
            fail("Expected timeout exception");
        } catch (Exception timeoutException) {
            StackTraceElement[] stackTrace = timeoutException.getStackTrace();
            boolean stackTraceContainsTheRealCauseOfTheTimeout = false;
            boolean stackTraceContainsOtherThanTheRealCauseOfTheTimeout = false;
            for (StackTraceElement element : stackTrace) {
                String methodName = element.getMethodName();
                if ("theRealCauseOfTheTimeout".equals(methodName)) {
                    stackTraceContainsTheRealCauseOfTheTimeout = true;
                }
                if ("notTheRealCauseOfTheTimeout".equals(methodName)) {
                    stackTraceContainsOtherThanTheRealCauseOfTheTimeout = true;
                }
            }
            assertTrue(
                    "Stack trace does not contain the real cause of the timeout",
                    stackTraceContainsTheRealCauseOfTheTimeout);
            assertFalse(
                    "Stack trace contains other than the real cause of the timeout, which can be very misleading",
                    stackTraceContainsOtherThanTheRealCauseOfTheTimeout);
        }
    }