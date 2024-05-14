public void mouseMoved(MouseEvent me) {
	//- RedrawManager.lock();
	translateMouseEvent(me);
	Globals.curEditor(this);
	setUnderMouse(me);
        Fig currentFig = getCurrentFig();
	if (currentFig != null && Globals.getShowFigTips()) {
	    String tip = currentFig.getTipString(me);
	    if (tip != null && (getJComponent() != null)) {
	        JComponent c = getJComponent();
	        if (c.getToolTipText() == null
		    || !(c.getToolTipText().equals(tip))) {
	            c.setToolTipText(tip);
	        }
            }
	} else if (getJComponent() != null
		   && getJComponent().getToolTipText() != null) {
            getJComponent().setToolTipText(null); //was ""
	}

	_selectionManager.mouseMoved(me);
	_modeManager.mouseMoved(me);
	//- RedrawManager.unlock();
	//- _redrawer.repairDamage();
    }