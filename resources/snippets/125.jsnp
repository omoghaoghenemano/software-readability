private Exception createTimeoutException(Thread thread) {
        StackTraceElement[] stackTrace = thread.getStackTrace();
        final Thread stuckThread = fLookForStuckThread ? getStuckThread(thread) : null;
        Exception currThreadException = new Exception(String.format(
                "test timed out after %d %s", fTimeout, fTimeUnit.name().toLowerCase()));
        if (stackTrace != null) {
            currThreadException.setStackTrace(stackTrace);
            thread.interrupt();
        }
        if (stuckThread != null) {
            Exception stuckThreadException = 
                new Exception ("Appears to be stuck in thread " +
                               stuckThread.getName());
            stuckThreadException.setStackTrace(getStackTrace(stuckThread));
            return new MultipleFailureException    
                (Arrays.<Throwable>asList(currThreadException, stuckThreadException));
        } else {
            return currThreadException;
        }
    }