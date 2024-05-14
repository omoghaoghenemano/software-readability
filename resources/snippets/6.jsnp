/**
     * {@inheritDoc}
     *
     * Overridden to find the start of a line-end.
     */
    public boolean addChar(char c) {
	if (c == '\n') {
	    hasLf = true;
	    return true;
	}

	if (c == '\r') {
	    hasCr = true;
	    return true;
	}

	return false;
    }