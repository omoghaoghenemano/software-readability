@Override
	protected void beforeTransactionCommit() {
		transactionCoordinator().sendBeforeTransactionCompletionNotifications( this );

		final boolean flush = ! transactionCoordinator().getTransactionContext().isFlushModeNever() &&
				( isDriver || ! transactionCoordinator().getTransactionContext().isFlushBeforeCompletionEnabled() );

		if ( flush ) {
			// if an exception occurs during flush, user must call rollback()
			transactionCoordinator().getTransactionContext().managedFlush();
		}

		if ( isDriver && isInitiator ) {
			transactionCoordinator().getTransactionContext().beforeTransactionCompletion( this );
		}

		closeIfRequired();
	}