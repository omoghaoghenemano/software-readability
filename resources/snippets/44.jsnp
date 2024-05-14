/**
     * Find an element in the list.
     *
     * This is a little more complex than the simple lookup since it might be
     * that we are indexing with a class and the list contains interfaces.
     *
     * Since the hashtable lookup is a lot faster than the linear search we
     * add the result of the linear search to the hashtable so that the next
     * time we need not do it.
     *
     * @return Checklist or null if noone exist.
     * @param cls the class to lookup.
     */
    private static Checklist lookupChecklist(Class cls) {
        if (lists.contains(cls)) {
            return (Checklist) lists.get(cls);
	}

        // Now lets search
        Enumeration enumeration = lists.keys();

        while (enumeration.hasMoreElements()) {
            Object clazz = enumeration.nextElement();

            Class[] intfs = cls.getInterfaces();
            for (int i = 0; i < intfs.length; i++) {
                if (intfs[i].equals(clazz)) {
                    // We found it!
                    Checklist chlist = (Checklist) lists.get(clazz);

                    // Enter the class to speed up the next search.
                    lists.put(cls, chlist);
                    return chlist;
                }
            }
        }

        return null;
    }