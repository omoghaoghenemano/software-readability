@Test
	@Resources(annotatedClasses = {
			SubclassOfSingleTableInheritance.class,
			SingleEntity.class,
			RootOfSingleTableInheritance.class,
			OtherSubclassOfSingleTableInheritance.class,
			SubclassOfSubclassOfSingleTableInheritance.class
	})
	public void testPreOrderRootSubEntityClosure() {
		EntityBinding rootEntityBinding = getEntityBinding( RootOfSingleTableInheritance.class );
		EntityBinding subclassEntityBinding = getEntityBinding( SubclassOfSingleTableInheritance.class );
		EntityBinding otherSubclassEntityBinding = getEntityBinding( OtherSubclassOfSingleTableInheritance.class );
		EntityBinding subclassOfSubclassEntityBinding = getEntityBinding( SubclassOfSubclassOfSingleTableInheritance.class );
		// need to figure out the order of direct subclasses, since it's indeterminate
		Iterator<EntityBinding> directEntityBindingIterator = rootEntityBinding.getDirectSubEntityBindings().iterator();
		boolean isSubclassEntityBindingFirst = subclassEntityBinding == directEntityBindingIterator.next();
		assertEquals( 3, rootEntityBinding.getSubEntityBindingClosureSpan() );
		Iterator<EntityBinding> subEntityBindingIterator = rootEntityBinding.getPreOrderSubEntityBindingClosure().iterator();
		assertTrue( subEntityBindingIterator.hasNext() );
		if ( isSubclassEntityBindingFirst ) {
			assertSame( subclassEntityBinding, subEntityBindingIterator.next() );
			assertTrue( subEntityBindingIterator.hasNext() );
			assertSame( subclassOfSubclassEntityBinding, subEntityBindingIterator.next() );
			assertTrue( subEntityBindingIterator.hasNext() );
			assertSame( otherSubclassEntityBinding, subEntityBindingIterator.next() );
		}
		else {
			assertSame( otherSubclassEntityBinding, subEntityBindingIterator.next() );
			assertTrue( subEntityBindingIterator.hasNext() );
			assertSame( subclassEntityBinding, subEntityBindingIterator.next() );
			assertTrue( subEntityBindingIterator.hasNext() );
			assertSame( subclassOfSubclassEntityBinding, subEntityBindingIterator.next() );
		}
		assertFalse( subEntityBindingIterator.hasNext() );
	}