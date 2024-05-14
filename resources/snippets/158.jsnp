/**
     * Construct a property panel for Node Instance elements.
     */
    public PropPanelNodeInstance() {
        super("Node Instance", lookupIcon("NodeInstance"),
                ConfigLoader.getTabPropsOrientation());

        addField(Translator.localize("label.name"), getNameTextField());
        addField(Translator.localize("label.namespace"),
                getNamespaceSelector());

        addSeparator();

        addField(Translator.localize("label.stimili-sent"),
                getStimuliSenderScroll());

        addField(Translator.localize("label.stimili-received"),
                getStimuliReceiverScroll());

        JList resList = new UMLLinkedList(new UMLContainerResidentListModel());
        addField(Translator.localize("label.residents"),
                new JScrollPane(resList));

        addSeparator();
        AbstractActionAddModelElement a =
            new ActionAddInstanceClassifier(Model.getMetaTypes().getNode());
        JScrollPane classifierScroll =
                new JScrollPane(new UMLMutableLinkedList(
	                new UMLInstanceClassifierListModel(),
	            a, null, null, true));
	addField(Translator.localize("label.classifiers"),
                    classifierScroll);


        addAction(new ActionNavigateContainerElement());
        addAction(new ActionNewStereotype());
        addAction(getDeleteAction());
    }