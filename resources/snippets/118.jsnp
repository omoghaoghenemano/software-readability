/**
     * The constructor.
     */
    public TabChecklist() {
	super("tab.checklist");

	tableModel = new TableModelChecklist(this);
	table.setModel(tableModel);

	Font labelFont = LookAndFeelMgr.getInstance().getStandardFont();
	table.setFont(labelFont);

	table.setIntercellSpacing(new Dimension(0, 1));
	table.setShowVerticalLines(false);
	table.getSelectionModel().addListSelectionListener(this);
	table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

	TableColumn checkCol = table.getColumnModel().getColumn(0);
	TableColumn descCol = table.getColumnModel().getColumn(1);
	checkCol.setMinWidth(20);
	checkCol.setMaxWidth(30);
	checkCol.setWidth(30);
	descCol.setPreferredWidth(900);
	table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	table.sizeColumnsToFit(-1);

	JScrollPane sp = new JScrollPane(table);

	setLayout(new BorderLayout());
	add(new JLabel(Translator.localize("tab.checklist.warning")),
	    BorderLayout.NORTH);
	add(sp, BorderLayout.CENTER);
	
	addComponentListener(this);
    }