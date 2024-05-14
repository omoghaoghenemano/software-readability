private boolean saveGraphicsToFile(File theFile, CmdSaveGraphics cmd,
            boolean overwrite) throws IOException {
        if ( theFile.exists() && !overwrite ) {
            int response =
		JOptionPane.showConfirmDialog(ArgoFrame.getInstance(),
                    Translator.messageFormat("optionpane.confirm-overwrite",
                            new Object[] {theFile}),
                    Translator.localize("optionpane.confirm-overwrite-title"),
                    JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.NO_OPTION) return false;
        }
        FileOutputStream fo = null;
        try {
            fo = new FileOutputStream( theFile );
            cmd.setStream(fo);
            cmd.setScale(Configuration.getInteger(
                    SaveGraphicsManager.KEY_GRAPHICS_RESOLUTION, 1));
            cmd.doIt();
        } finally {
            if (fo != null) {
                fo.close();
            }
        }
        return true;
    }