/**
	 * Get the value mapped to this key, or null if no value is mapped to this key.
	 *
	 * @param key The cache key
	 *
	 * @return The cached data
	 */
	public final Object get(Object key) {
		try {
			final Element element = getCache().get( key );
			if ( element == null ) {
				return null;
			}
			else {
				return element.getObjectValue();
			}
		}
		catch (net.sf.ehcache.CacheException e) {
			if ( e instanceof NonStopCacheException ) {
				HibernateNonstopCacheExceptionHandler.getInstance()
						.handleNonstopCacheException( (NonStopCacheException) e );
				return null;
			}
			else {
				throw new CacheException( e );
			}
		}
	}