@Test
	@Resources(annotatedClasses = {
			SubclassOfSingleTableInheritance.class,
			SingleEntity.class,
			RootOfSingleTableInheritance.class,
			OtherSubclassOfSingleTableInheritance.class,
			SubclassOfSubclassOfSingleTableInheritance.class
	})
	public void testNoPolymorphism() {
		EntityBinding noInheritanceEntityBinding = getEntityBinding( SingleEntity.class );
		assertTrue( "SingleEntity should be a root entity", noInheritanceEntityBinding.isRoot() );
		assertNull( noInheritanceEntityBinding.getSuperEntityBinding() );
		assertSame( noInheritanceEntityBinding, getRootEntityBinding( SingleEntity.class ) );
		assertFalse( noInheritanceEntityBinding.isPolymorphic() );
		assertFalse( noInheritanceEntityBinding.hasSubEntityBindings() );
		assertEquals( 0, noInheritanceEntityBinding.getSubEntityBindingClosureSpan() );
		assertFalse( noInheritanceEntityBinding.getPostOrderSubEntityBindingClosure().iterator().hasNext() );
		assertFalse( noInheritanceEntityBinding.getPreOrderSubEntityBindingClosure().iterator().hasNext() );
		Set<AttributeBinding> directAttributeBindings = new HashSet<AttributeBinding>();
		for ( AttributeBinding attributeBinding : noInheritanceEntityBinding.attributeBindings() ) {
			assertTrue( directAttributeBindings.add( attributeBinding ) );
		}
		assertEquals( 1, directAttributeBindings.size() );
		assertSame(
				noInheritanceEntityBinding.getHierarchyDetails().getEntityIdentifier().getValueBinding(),
				directAttributeBindings.iterator().next()
		);
		assertEquals( 1, noInheritanceEntityBinding.getAttributeBindingClosureSpan() );
		Iterator<AttributeBinding> iterator = noInheritanceEntityBinding.attributeBindings().iterator();
		assertTrue( iterator.hasNext() );
		assertSame( noInheritanceEntityBinding.getHierarchyDetails().getEntityIdentifier().getValueBinding(), iterator.next() );
		assertFalse( iterator.hasNext() );
		iterator = noInheritanceEntityBinding.getAttributeBindingClosure().iterator();
		assertTrue( iterator.hasNext() );
		assertSame( noInheritanceEntityBinding.getHierarchyDetails().getEntityIdentifier().getValueBinding(), iterator.next() );
		assertFalse( iterator.hasNext() );
		iterator =  noInheritanceEntityBinding.getSubEntityAttributeBindingClosure().iterator();
		assertTrue( iterator.hasNext() );
		assertSame( noInheritanceEntityBinding.getHierarchyDetails().getEntityIdentifier().getValueBinding(), iterator.next() );
		assertFalse( iterator.hasNext() );
	}