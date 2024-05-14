public void targetAdded(TargetEvent e) {
            if (!updatingSelection) {
                updatingSelection = true;
                Object[] targets = e.getAddedTargets();

                int rows = getRowCount();
                for (int i = 0; i < targets.length; i++) {
                    Object target = targets[i];
                    if (target instanceof Fig) {
                        target = ((Fig) target).getOwner();
                    }
                    for (int j = 0; j < rows; j++) {
                        Object rowItem =
                            ((DefaultMutableTreeNode)
                                    getPathForRow(j).getLastPathComponent())
                            .getUserObject();
                        if (rowItem == target) {
                            updatingSelectionViaTreeSelection = true;
                            addSelectionRow(j);
                            updatingSelectionViaTreeSelection = false;
                        }
                    }
                }

                if (getSelectionCount() > 0) {
                    scrollRowToVisible(getSelectionRows()[0]);
                }
                updatingSelection = false;
            }
            // setTargets(e.getNewTargets());
        }