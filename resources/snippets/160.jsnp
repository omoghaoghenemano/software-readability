@Test
    public void throwTimeoutExceptionOnSecondCallAlthoughFirstCallThrowsException()
            throws Throwable {
        thrown.expectMessage("test timed out after 100 milliseconds");
        try {
            evaluateWithException(new RuntimeException());
        } catch (Throwable expected) {
        }
        evaluateWithWaitDuration(TIMEOUT + 50);
    }