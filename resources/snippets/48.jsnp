/**
     * @param filename the filename to read from
     */
    public void read(String filename) {
        try {
            FileReader f = new FileReader(filename);
            BufferedReader fr = new BufferedReader(f);

            String line = "";
            String content = "";
            boolean inSection = false;
            while (line != null) {
                line = fr.readLine();
                if (line != null) {
                    if (inSection) {
                        String sectionId = getSectId(line);
                        if (sectionId != null) {
                            inSection = false;
                            mAry.put(sectionId, content);
                            content = "";
                        } else {
                            content += line + LINE_SEPARATOR;
                        }
                    } else {
                        String sectionId = getSectId(line);
                        if (sectionId != null) {
                            inSection = true;
                        }
                    }
                }
            }
            fr.close();
        } catch (IOException e) {
            LOG.error("Error: " + e.toString());
        }
    }