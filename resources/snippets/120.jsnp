public List getInEdges(Object port) {
	Vector res = new Vector(); //wasteful!
	if (Model.getFacade().isAClassifierRole(port)) {
	    Object cr = port;
	    Collection ends = Model.getFacade().getAssociationEnds(cr);
	    if (ends == null) {
                return res; // empty Vector
            }
	    Iterator iter = ends.iterator();
	    while (iter.hasNext()) {
		Object aer = iter.next();
		res.addElement(Model.getFacade().getAssociation(aer));
	    }
	}
	return res;
    }
