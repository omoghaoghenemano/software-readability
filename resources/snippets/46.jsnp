public void toDoItemsRemoved(ToDoListEvent tde) {
	LOG.debug("toDoItemRemoved");
        Vector items = tde.getToDoItems();
        int nItems = items.size();
        
	ToDoList list = Designer.theDesigner().getToDoList(); //source?
	Object[] path = new Object[2];
	path[0] = Designer.theDesigner().getToDoList();


	Enumeration elems = list.getPosters().elements();
 	while (elems.hasMoreElements()) {
	    Poster p = (Poster) elems.nextElement();
            boolean anyInPoster = false;
            for (int i = 0; i < nItems; i++) {
                ToDoItem item = (ToDoItem) items.elementAt(i);
                Poster post = item.getPoster();
                if (post == p) { 
                    anyInPoster = true;
                    break;
                }
            }
            if (!anyInPoster) { 
                continue;
            }
	    path[1] = p;
	    fireTreeStructureChanged(path);
	}
    }