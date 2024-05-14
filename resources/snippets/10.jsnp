private void myDoubleClick(Object src) {
	Object sel = null;
	Diagram d = null;
	if (src == resultsTable) {
	    int row = resultsTable.getSelectionModel().getMinSelectionIndex();
	    if (row < 0) {
                return;
            }
	    sel = results.elementAt(row);
	    d = (Diagram) diagrams.elementAt(row);
	} else if (src == relatedTable) {
	    int row = relatedTable.getSelectionModel().getMinSelectionIndex();
	    if (row < 0) {
                return;
            }
	    numJumpToRelated++;
	    sel = related.elementAt(row);
	}

	if (d != null) {
            LOG.debug("go " + sel + " in " + d.getName());
            TargetManager.getInstance().setTarget(d);
        }
	TargetManager.getInstance().setTarget(sel);
    }