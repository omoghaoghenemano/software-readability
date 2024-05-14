public void updateListener(PropertyChangeListener listener, 
            Object modelElement,
            PropertyChangeEvent pce) {
        Object obj = pce.getSource();
        if ((obj == modelElement) 
                && "stereotype".equals(pce.getPropertyName())) {
            if (pce instanceof AddAssociationEvent 
                    && Model.getFacade().isAStereotype(pce.getNewValue())) {
                // new stereotype
                addElementListener(
                        listener, 
                        pce.getNewValue(), 
                        new String[] {"name", "remove"});
            }
            if (pce instanceof RemoveAssociationEvent 
                    && Model.getFacade().isAStereotype(pce.getOldValue())) {
                // removed stereotype
                removeElementListener(
                        listener, 
                        pce.getOldValue());
            }
        }
    }