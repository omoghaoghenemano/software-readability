@Test public void testHHH1780() throws Exception {
        // verifies the tree contains a NOT->EXISTS subtree
        class Verifier {
            public boolean verify(AST root) {
                Stack<AST> queue = new Stack<AST>();
                queue.push( root );
                while ( !queue.isEmpty() ) {
                    AST parent = queue.pop();
                    AST child = parent.getFirstChild();
                    while ( child != null ) {
                        if ( parent.getType() == HqlTokenTypes.NOT &&
                                child.getType() == HqlTokenTypes.EXISTS ) {
                            return true;
                        }
                        queue.push( child );
                        child = child.getNextSibling();
                    }
                }
                return false;
            }
        }

        // test inversion of AND
        AST ast = doParse(
                "from Person p where not ( p.name is null and exists(select a.id from Address a where a.id=p.id))",
                false
        );

        assertTrue( new Verifier().verify( ast ) );

        // test inversion of OR
        ast = doParse(
                "from Person p where not ( p.name is null or exists(select a.id from Address a where a.id=p.id))",
                false
        );

        assertTrue( new Verifier().verify( ast ) );
    }