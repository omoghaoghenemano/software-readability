package de.uni_passau.fim.se2.sa.readability;

import de.uni_passau.fim.se2.sa.readability.features.FeatureMetric;

public class DummyFeatureMetric extends FeatureMetric {

    @Override
    public double computeMetric(String codeSnippet) {
        // Dummy implementation for testing
        return codeSnippet.length();
    }

    @Override
    public String getIdentifier() {
        // Dummy implementation for testing
        return "DummyFeatureMetric";
    }
}