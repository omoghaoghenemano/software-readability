/**
	 * Perform {@link org.hibernate.action.spi.Executable#execute()} on each element of the list
	 * 
	 * @param list The list of Executable elements to be performed
	 *
	 * @throws HibernateException
	 */
	private <E extends Executable & Comparable<?> & Serializable> void executeActions(ExecutableList<E> list) throws HibernateException {
		// todo : consider ways to improve the double iteration of Executables here:
		//		1) we explicitly iterate list here to perform Executable#execute()
		//		2) ExecutableList#getQuerySpaces also iterates the Executables to collect query spaces.
		try {
			for ( E e : list ) {
				try {
					e.execute();
				}
				finally {
					beforeTransactionProcesses.register( e.getBeforeTransactionCompletionProcess() );
					afterTransactionProcesses.register( e.getAfterTransactionCompletionProcess() );
				}
			}
		}
		finally {
			if ( session.getFactory().getSettings().isQueryCacheEnabled() ) {
				// Strictly speaking, only a subset of the list may have been processed if a RuntimeException occurs.
				// We still invalidate all spaces. I don't see this as a big deal - after all, RuntimeExceptions are
				// unexpected.
				Set<Serializable> propertySpaces = list.getQuerySpaces();
				invalidateSpaces( propertySpaces.toArray( new Serializable[propertySpaces.size()] ) );
			}
		}

		list.clear();
		session.getTransactionCoordinator().getJdbcCoordinator().executeBatch();
	}