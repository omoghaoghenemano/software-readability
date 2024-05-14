/**
     * static initializer, register all appropriate notations.
     */
    static void init() {
        NotationProviderFactory2 npf = NotationProviderFactory2.getInstance();
        NotationName name = /*Notation.findNotation("Java");*/
            Notation.makeNotation(
                    "Java",
                    null,
                    ResourceLoaderWrapper.lookupIconResource("JavaNotation"));

        npf.addNotationProvider(
                NotationProviderFactory2.TYPE_NAME,
                name, ModelElementNameNotationJava.class);
        npf.addNotationProvider(
                NotationProviderFactory2.TYPE_ATTRIBUTE,
                name, AttributeNotationJava.class);
        npf.addNotationProvider(
                NotationProviderFactory2.TYPE_OPERATION,
                name, OperationNotationJava.class);
        npf.addNotationProvider(
                NotationProviderFactory2.TYPE_ASSOCIATION_END_NAME,
                name, AssociationEndNameNotationJava.class);
        npf.addNotationProvider(
                NotationProviderFactory2.TYPE_ASSOCIATION_NAME,
                name, AssociationNameNotationJava.class);
    }