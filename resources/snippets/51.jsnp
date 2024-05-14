/**
     * Saves the given actions in the configuration file
     * 
     * @param newActions
     *            the actions array
     */
    public static void saveShortcuts(Action[] newActions) {
        for (int i = 0; i < newActions.length; i++) {
            Action oldAction = (Action) shortcutHash
                    .get(newActions[i].getKey());
            if (newActions[i].getCurrentShortcut() == null
                    && newActions[i].getDefaultShortcut() != null) {
                // if a default action was voided then we have to save it
                Configuration.setString(Configuration.makeKey(oldAction
                        .getKey()), "");
            } else if (newActions[i].getCurrentShortcut() != null
                    && !newActions[i].getCurrentShortcut().equals(
                            newActions[i].getDefaultShortcut())) {
                // if a not-default current shortcut was added, then we have to
                // save it
                Configuration.setString(Configuration.makeKey(oldAction
                        .getKey()), KeyEventUtils.formatKeyStroke(newActions[i]
                        .getCurrentShortcut()));
            } else {
                // if the actual is not going to be saved, then try to remove it
                // (as it could have been cancelled)
                Configuration.removeKey(Configuration.makeKey(oldAction
                        .getKey()));
            }
        }
    }