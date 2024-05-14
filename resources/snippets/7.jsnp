/**
     * Loads the bundle (if not already loaded).
     *
     * @param name The name of the bundle to load.
     */
    private static void loadBundle(String name) {
        if (bundles.containsKey(name)) {
            return;
        }
        String resource = BUNDLES_PATH + "." + name;
        ResourceBundle bundle = null;
        try {
            LOG.debug("Loading " + resource);
            bundle = ResourceBundle.getBundle(resource, Locale.getDefault());
        } catch (MissingResourceException e1) {
            LOG.debug("Resource " + resource
		      + " not found in the default class loader.");

	    Iterator iter = classLoaders.iterator();
	    while (iter.hasNext()) {
		ClassLoader cl = (ClassLoader) iter.next();
		try {
		    LOG.debug("Loading " + resource + " from " + cl);
		    bundle =
			ResourceBundle.getBundle(resource,
						 Locale.getDefault(),
						 cl);
		    break;
		} catch (MissingResourceException e2) {
		    LOG.debug("Resource " + resource + " not found in " + cl);
		}
	    }
        }

        bundles.put(name, bundle);
    }