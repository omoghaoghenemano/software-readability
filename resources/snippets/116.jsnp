public void write(BufferedReader reader,
                      BufferedWriter writer,
                      Stack parseStateStack) throws IOException {
        ParseState parseState = (ParseState) parseStateStack.peek();
        Object mInterface = /*(MInterface)*/ parseState.newClassifier(name);

	if (mInterface != null) {
	    parseStateStack.push(new ParseState(mInterface));
	    StringBuffer sbText =
		GeneratorJava.getInstance().generateClassifierStart(mInterface);
	    if (sbText != null) {
		writer.write (sbText.toString());
	    }
            // dispose code piece in reader
            ffCodePiece(reader, null);
        } else {
            // not in model, so write the original code
            ffCodePiece(reader, writer);
        }
    }