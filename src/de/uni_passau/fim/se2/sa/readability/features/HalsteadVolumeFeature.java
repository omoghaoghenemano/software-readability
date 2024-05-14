package de.uni_passau.fim.se2.sa.readability.features;

public class HalsteadVolumeFeature extends FeatureMetric {

    /**
     * Computes the Halstead Volume metric based on the given code snippet.
     *
     * @return Halstead Volume of the given code snippet.
     */
    @Override
    public double computeMetric(String codeSnippet) {
        // Implement the Halstead Volume metric using the OperandVisitor and OperatorVisitor.
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public String getIdentifier() {
        return "HalsteadVolume";
    }
}
