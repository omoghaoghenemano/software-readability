private EntityManager internalCreateEntityManager(SynchronizationType synchronizationType, Map map) {
		validateNotClosed();

		//TODO support discardOnClose, persistencecontexttype?, interceptor,
		return new EntityManagerImpl(
				this,
				PersistenceContextType.EXTENDED,
				synchronizationType,
				transactionType,
				discardOnClose,
				sessionInterceptorClass,
				map
		);
	}