@Override
	public void configure(Configuration cfg) {
		super.configure( cfg );
		cfg.setProperty( Environment.USE_SECOND_LEVEL_CACHE, "true" );
		cfg.setProperty( Environment.GENERATE_STATISTICS, "true" );
		cfg.setProperty( Environment.USE_QUERY_CACHE, "false" );
		cfg.setProperty( Environment.CACHE_REGION_FACTORY, getCacheRegionFactory().getName() );
		cfg.setProperty( Environment.TRANSACTION_STRATEGY, getTransactionFactoryClass().getName() );
		cfg.getProperties().put( AvailableSettings.JTA_PLATFORM, getJtaPlatform() );
		cfg.setProperty( Environment.CONNECTION_PROVIDER, getConnectionProviderClass().getName() );
	}