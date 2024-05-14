public boolean equals(Table table) {
		if (null == table) {
			return false;
		}
		if (this == table) {
			return true;
		}

		return isQuoted() ? name.equals(table.getName()) : name.equalsIgnoreCase(table.getName())
			&& ((schema == null && table.getSchema() != null) ? false : (schema == null) ? true : isSchemaQuoted() ? schema.equals(table.getSchema()) : schema.equalsIgnoreCase(table.getSchema()))
			&& ((catalog == null && table.getCatalog() != null) ? false : (catalog == null) ? true : isCatalogQuoted() ? catalog.equals(table.getCatalog()) : catalog.equalsIgnoreCase(table.getCatalog()));
	}