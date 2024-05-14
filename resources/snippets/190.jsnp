protected void resetRegionUsageState(CacheAccessListener localListener, CacheAccessListener remoteListener) {
		String stdName = StandardQueryCache.class.getName();
		String acctName = Account.class.getName();

		localListener.getSawRegionModification( stdName );
		localListener.getSawRegionModification( acctName );

		localListener.getSawRegionAccess( stdName );
		localListener.getSawRegionAccess( acctName );

		remoteListener.getSawRegionModification( stdName );
		remoteListener.getSawRegionModification( acctName );

		remoteListener.getSawRegionAccess( stdName );
		remoteListener.getSawRegionAccess( acctName );

		log.info( "Region usage state cleared" );
	}