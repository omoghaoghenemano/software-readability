protected synchronized String evalToString(
            Object self,
            String expr,
            String sep)
    	throws ExpansionException {

        _scratchBindings.put("self", self);
        java.util.List values = eval(_scratchBindings, expr);
        _strBuf.setLength(0);
        Iterator iter = values.iterator();
        while (iter.hasNext()) {
            Object v = iter.next();
            if (Model.getFacade().isAModelElement(v)) {
                v = Model.getFacade().getName(v);
                if ("".equals(v)) {
                    v = Translator.localize("misc.name.anon");
                }
            }
            if (Model.getFacade().isAExpression(v)) {
                v = Model.getFacade().getBody(v);
                if ("".equals(v)) {
                    v = "(unspecified)";
                }
            }
            if (!"".equals(v)) {
                _strBuf.append(v);
                if (iter.hasNext()) {
                    _strBuf.append(sep);
                }
            }
        }
        return _strBuf.toString();
    }