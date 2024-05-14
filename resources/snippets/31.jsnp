private static void parseFilter(Element filterElement, Filterable filterable, Mappings model) {
		final String name = filterElement.attributeValue( "name" );
		String condition = filterElement.getTextTrim();
		if ( StringHelper.isEmpty(condition) ) {
			condition = filterElement.attributeValue( "condition" );
		}
		//TODO: bad implementation, cos it depends upon ordering of mapping doc
		//      fixing this requires that Collection/PersistentClass gain access
		//      to the Mappings reference from Configuration (or the filterDefinitions
		//      map directly) sometime during Configuration.build
		//      (after all the types/filter-defs are known and before building
		//      persisters).
		if ( StringHelper.isEmpty(condition) ) {
			condition = model.getFilterDefinition(name).getDefaultFilterCondition();
		}
		if ( condition==null) {
			throw new MappingException("no filter condition found for filter: " + name);
		}
		Iterator aliasesIterator = filterElement.elementIterator("aliases");
		java.util.Map<String, String> aliasTables = new HashMap<String, String>();
		while (aliasesIterator.hasNext()){
			Element alias = (Element) aliasesIterator.next();
			aliasTables.put(alias.attributeValue("alias"), alias.attributeValue("table"));
		}
		LOG.debugf( "Applying filter [%s] as [%s]", name, condition );
		String autoAliasInjectionText = filterElement.attributeValue("autoAliasInjection");
		boolean autoAliasInjection = StringHelper.isEmpty(autoAliasInjectionText) ? true : Boolean.parseBoolean(autoAliasInjectionText);
		filterable.addFilter(name, condition, autoAliasInjection, aliasTables, null);
	}