/**
     * Notify all listeners that have registered interest for
     * notification on this event type.  The event instance
     * is lazily created using the parameters passed into
     * the fire method.
     * @see EventListenerList
     */
    public void fireTreeStructureChanged(
					 Object source,
					 Object[] path,
					 int[] childIndices,
					 Object[] children) {

        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        TreeModelEvent e = null;
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == TreeModelListener.class) {
                // Lazily create the event:
                if (e == null)
                    e =
                        new TreeModelEvent(
					   source,
					   path,
					   childIndices,
					   children);
                ((TreeModelListener) listeners[i + 1]).treeStructureChanged(e);
            }
        }
    }