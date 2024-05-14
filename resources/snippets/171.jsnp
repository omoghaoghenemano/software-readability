private void addTransactionFactories(StrategySelectorImpl strategySelector) {
		strategySelector.registerStrategyImplementor( TransactionFactory.class, JdbcTransactionFactory.SHORT_NAME, JdbcTransactionFactory.class );
		strategySelector.registerStrategyImplementor( TransactionFactory.class, "org.hibernate.transaction.JDBCTransactionFactory", JdbcTransactionFactory.class );

		strategySelector.registerStrategyImplementor( TransactionFactory.class, JtaTransactionFactory.SHORT_NAME, JtaTransactionFactory.class );
		strategySelector.registerStrategyImplementor( TransactionFactory.class, "org.hibernate.transaction.JTATransactionFactory", JtaTransactionFactory.class );

		strategySelector.registerStrategyImplementor( TransactionFactory.class, CMTTransactionFactory.SHORT_NAME, CMTTransactionFactory.class );
		strategySelector.registerStrategyImplementor( TransactionFactory.class, "org.hibernate.transaction.CMTTransactionFactory", CMTTransactionFactory.class );
	}