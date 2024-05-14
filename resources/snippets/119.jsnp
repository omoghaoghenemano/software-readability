public void vetoableChange(PropertyChangeEvent pce) {
        if ("ownedElement".equals(pce.getPropertyName())) {
            Vector oldOwned = (Vector) pce.getOldValue();
            Object eo = pce.getNewValue();
            Object me = Model.getFacade().getModelElement(eo);
            if (oldOwned.contains(eo)) {
                LOG.debug("model removed " + me);
                if (Model.getFacade().isANode(me)) {
                    removeNode(me);
                }
                if (Model.getFacade().isANodeInstance(me)) {
                    removeNode(me);
                }
                if (Model.getFacade().isAComponent(me)) {
                    removeNode(me);
                }
                if (Model.getFacade().isAComponentInstance(me)) {
                    removeNode(me);
                }
                if (Model.getFacade().isAClass(me)) {
                    removeNode(me);
                }
                if (Model.getFacade().isAInterface(me)) {
                    removeNode(me);
                }
                if (Model.getFacade().isAObject(me)) {
                    removeNode(me);
                }
                if (Model.getFacade().isAAssociation(me)) {
                    removeEdge(me);
                }
                if (Model.getFacade().isADependency(me)) {
                    removeEdge(me);
                }
                if (Model.getFacade().isALink(me)) {
                    removeEdge(me);
                }
            } else {
                LOG.debug("model added " + me);
            }
        }
    }