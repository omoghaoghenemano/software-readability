public Set getDependencies(Object parent) {
        if (Model.getFacade().isAClass(parent)) {
	    Set set = new HashSet();
	    set.add(parent);
	    set.addAll(Model.getFacade().getAttributes(parent));
	    set.addAll(Model.getFacade().getOperations(parent));
	    set.addAll(Model.getFacade().getAssociationEnds(parent));
	    set.addAll(Model.getFacade().getSupplierDependencies(parent));
	    set.addAll(Model.getFacade().getClientDependencies(parent));
	    set.addAll(Model.getFacade().getGeneralizations(parent));
	    set.addAll(Model.getFacade().getSpecializations(parent));
	    return set;
	}

	return null;
    }