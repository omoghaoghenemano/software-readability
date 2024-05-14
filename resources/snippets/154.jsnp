protected void modelChanged(PropertyChangeEvent mee) {
        super.modelChanged(mee);
        final Object trCollection = mee.getNewValue();
        final String eName = mee.getPropertyName();
        final Object owner = getOwner();
        /*
         * A Concurrent region cannot have incoming or outgoing transitions so
         * incoming or outgoing transitions are redirected to its concurrent
         * composite state container.
         */
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Object tr = null;
                // TODO: Is this comparison correct?
                // Where is the string created?
                if (eName == "incoming") {
                    if (!((Collection) trCollection).isEmpty()) {
                        tr = ((Collection) trCollection).iterator().next();
                    }
                    if (tr != null
                            && Model.getFacade().isATransition(tr)) {
                        Model.getCommonBehaviorHelper().setTarget(tr,
                                Model.getFacade().getContainer(owner));
                    }
                } else if (eName == "outgoing") {
                    if (!((Collection) trCollection).isEmpty()) {
                        tr = ((Collection) trCollection).iterator().next();
                    }
                    if (tr != null
                            && Model.getFacade().isATransition(tr))
                    {
                        Model.getStateMachinesHelper().setSource(tr,
                                Model.getFacade().getContainer(owner));
                    }
                }
            }
        });
    }