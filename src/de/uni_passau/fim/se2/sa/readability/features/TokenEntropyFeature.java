package de.uni_passau.fim.se2.sa.readability.features;

public class TokenEntropyFeature extends FeatureMetric {

    /**
     * Computes the entropy metric based on the tokens of the given code snippet.
     * Since we are interested in the readability of code as perceived by a human, tokens also include whitespaces and the like.
     *
     * @return token entropy of the given code snippet.
     */
    @Override
    public double computeMetric(String codeSnippet) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public String getIdentifier() {
        return "TokenEntropy";
    }
}
