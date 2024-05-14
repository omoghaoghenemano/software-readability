public String[] toColumns(String alias, String propertyName) throws QueryException {
		if ( propertyName.equals(CollectionPropertyNames.COLLECTION_ELEMENTS) ) {
			return memberPersister.getElementColumnNames(alias);
		}
		else if ( propertyName.equals(CollectionPropertyNames.COLLECTION_INDICES) ) {
			if ( !memberPersister.hasIndex() ) throw new QueryException("unindexed collection in indices()");
			return memberPersister.getIndexColumnNames(alias);
		}
		else if ( propertyName.equals(CollectionPropertyNames.COLLECTION_SIZE) ) {
			String[] cols = memberPersister.getKeyColumnNames();
			return new String[] { "count(" + alias + '.' + cols[0] + ')' };
		}
		else if ( propertyName.equals(CollectionPropertyNames.COLLECTION_MAX_INDEX) ) {
			if ( !memberPersister.hasIndex() ) throw new QueryException("unindexed collection in maxIndex()");
			String[] cols = memberPersister.getIndexColumnNames(alias);
			if ( cols.length!=1 ) throw new QueryException("composite collection index in maxIndex()");
			return new String[] { "max(" + cols[0] + ')' };
		}
		else if ( propertyName.equals(CollectionPropertyNames.COLLECTION_MIN_INDEX) ) {
			if ( !memberPersister.hasIndex() ) throw new QueryException("unindexed collection in minIndex()");
			String[] cols = memberPersister.getIndexColumnNames(alias);
			if ( cols.length!=1 ) throw new QueryException("composite collection index in minIndex()");
			return new String[] { "min(" + cols[0] + ')' };
		}
		else if ( propertyName.equals(CollectionPropertyNames.COLLECTION_MAX_ELEMENT) ) {
			String[] cols = memberPersister.getElementColumnNames(alias);
			if ( cols.length!=1 ) throw new QueryException("composite collection element in maxElement()");
			return new String[] { "max(" + cols[0] + ')' };
		}
		else if ( propertyName.equals(CollectionPropertyNames.COLLECTION_MIN_ELEMENT) ) {
			String[] cols = memberPersister.getElementColumnNames(alias);
			if ( cols.length!=1 ) throw new QueryException("composite collection element in minElement()");
			return new String[] { "min(" + cols[0] + ')' };
		}
		else {
			//return memberPersister.toColumns(alias, propertyName);
			throw new QueryException("illegal syntax near collection: " + propertyName);
		}
	}