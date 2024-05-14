protected void scanAnnotatedMembers(Map<Class<? extends Annotation>, List<FrameworkMethod>> methodsForAnnotations, Map<Class<? extends Annotation>, List<FrameworkField>> fieldsForAnnotations) {
        for (Class<?> eachClass : getSuperClasses(fClass)) {
            for (Method eachMethod : MethodSorter.getDeclaredMethods(eachClass)) {
                addToAnnotationLists(new FrameworkMethod(eachMethod), methodsForAnnotations);
            }
            // ensuring fields are sorted to make sure that entries are inserted
            // and read from fieldForAnnotations in a deterministic order
            for (Field eachField : getSortedDeclaredFields(eachClass)) {
                addToAnnotationLists(new FrameworkField(eachField), fieldsForAnnotations);
            }
        }
    }