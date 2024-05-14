@Test
    public void shouldReturnOnlyTheNamedDataPoints() throws Throwable {
        SpecificDataPointsSupplier supplier = new SpecificDataPointsSupplier(new TestClass(TestClassWithNamedDataPoints.class));

        List<PotentialAssignment> assignments = supplier.getValueSources(signature("methodWantingAllNamedStrings"));
        List<String> assignedStrings = getStringValuesFromAssignments(assignments);

        assertEquals(4, assignedStrings.size());
        assertThat(assignedStrings, hasItems("named field", "named method", "named single value", "named single method value"));
    }