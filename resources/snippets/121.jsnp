/**
     * Displays visual indications of pending ToDoItems.
     * Please note that the list of advices (ToDoList) is not the same
     * as the list of element known by the FigNode (_figs). Therefore,
     * it is necessary to check if the graphic item exists before drawing
     * on it. See ClAttributeCompartment for an example.
     * @param g the graphics device
     * @see org.argouml.uml.cognitive.critics.ClAttributeCompartment
     */
    public void paintClarifiers(Graphics g) {
        int iconX = getX();
        int iconY = getY() - 10;
        ToDoList list = Designer.theDesigner().getToDoList();
        Vector items = list.elementsForOffender(getOwner());
        int size = items.size();
        for (int i = 0; i < size; i++) {
            ToDoItem item = (ToDoItem) items.elementAt(i);
            Icon icon = item.getClarifier();
            if (icon instanceof Clarifier) {
                ((Clarifier) icon).setFig(this);
                ((Clarifier) icon).setToDoItem(item);
            }
            if (icon != null) {
                icon.paintIcon(null, g, iconX, iconY);
                iconX += icon.getIconWidth();
            }
        }
        items = list.elementsForOffender(this);
        size = items.size();
        for (int i = 0; i < size; i++) {
            ToDoItem item = (ToDoItem) items.elementAt(i);
            Icon icon = item.getClarifier();
            if (icon instanceof Clarifier) {
                ((Clarifier) icon).setFig(this);
                ((Clarifier) icon).setToDoItem(item);
            }
            if (icon != null) {
                icon.paintIcon(null, g, iconX, iconY);
                iconX += icon.getIconWidth();
            }
        }
    }