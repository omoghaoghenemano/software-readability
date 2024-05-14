private void bindIndex(final Mappings mappings) {
		if ( !indexColumn.isImplicit() ) {
			PropertyHolder valueHolder = PropertyHolderBuilder.buildPropertyHolder(
					this.collection,
					StringHelper.qualify( this.collection.getRole(), "key" ),
					null,
					null, propertyHolder, mappings
			);
			List list = (List) this.collection;
			if ( !list.isOneToMany() ) indexColumn.forceNotNull();
			indexColumn.setPropertyHolder( valueHolder );
			SimpleValueBinder value = new SimpleValueBinder();
			value.setColumns( new Ejb3Column[] { indexColumn } );
			value.setExplicitType( "integer" );
			value.setMappings( mappings );
			SimpleValue indexValue = value.make();
			indexColumn.linkWithValue( indexValue );
			list.setIndex( indexValue );
			list.setBaseIndex( indexColumn.getBase() );
			if ( list.isOneToMany() && !list.getKey().isNullable() && !list.isInverse() ) {
				String entityName = ( (OneToMany) list.getElement() ).getReferencedEntityName();
				PersistentClass referenced = mappings.getClass( entityName );
				IndexBackref ib = new IndexBackref();
				ib.setName( '_' + propertyName + "IndexBackref" );
				ib.setUpdateable( false );
				ib.setSelectable( false );
				ib.setCollectionRole( list.getRole() );
				ib.setEntityName( list.getOwner().getEntityName() );
				ib.setValue( list.getIndex() );
				referenced.addProperty( ib );
			}
		}
		else {
			Collection coll = this.collection;
			throw new AnnotationException(
					"List/array has to be annotated with an @OrderColumn (or @IndexColumn): "
							+ coll.getRole()
			);
		}
	}