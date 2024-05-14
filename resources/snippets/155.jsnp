public void setEnclosingFig(Fig encloser) {
        if (getOwner() != null) {
            Object nod = getOwner();
            if (encloser != null) {
                Object comp = encloser.getOwner();
                if (Model.getFacade().isAComponentInstance(comp)) {
                    if (Model.getFacade().getComponentInstance(nod) != comp) {
                        Model.getCommonBehaviorHelper()
                                .setComponentInstance(nod, comp);
                        super.setEnclosingFig(encloser);
                    }
                } else if (Model.getFacade().isANode(comp)) {
                    super.setEnclosingFig(encloser);
                }
            } else if (encloser == null) {
                if (isVisible() 
                        // If we are not visible most likely 
                        // we're being deleted.
                    // TODO: This indicates a more fundamental problem that should
                    // be investigated - tfm - 20061230
                    && Model.getFacade().getComponentInstance(nod) 
                                    != null) {
                    Model.getCommonBehaviorHelper()
                            .setComponentInstance(nod, null);
                    super.setEnclosingFig(encloser);
                }
            }
        }

        if (getLayer() != null) {
            // elementOrdering(figures);
            Collection contents = new ArrayList(getLayer().getContents());
            Iterator it = contents.iterator();
            while (it.hasNext()) {
                Object o = it.next();
                if (o instanceof FigEdgeModelElement) {
                    FigEdgeModelElement figedge = (FigEdgeModelElement) o;
                    figedge.getLayer().bringToFront(figedge);
                }
            }
        }
    }