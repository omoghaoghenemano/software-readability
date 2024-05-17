package de.uni_passau.fim.se2.sa.readability.features;

import java.util.*;

public class TokenEntropyFeature extends FeatureMetric {

    /**
     * Computes the entropy metric based on the tokens of the given code snippet.
     * Since we are interested in the readability of code as perceived by a human, tokens also include whitespaces and the like.
     *
     * @return token entropy of the given code snippet.
     */
    @Override
    public double computeMetric(String codeSnippet) {
        // Parse the code snippet into tokens
        List<String> tokens = tokenizeCodeSnippet(codeSnippet);


        // Count frequencies of each token
        Map<String, Integer> tokenCounts = new HashMap<>();
        for (String token : tokens) {
            tokenCounts.put(token, tokenCounts.getOrDefault(token, 0) + 1);
        }

        // Calculate total number of tokens
        int totalTokens = tokens.size();

        // Calculate token probabilities and entropy
        double entropy = 0.0;
        for (String token : tokenCounts.keySet()) {
            double tokenProbability = (double) tokenCounts.get(token) / totalTokens;
            entropy -= tokenProbability * (Math.log(tokenProbability) / Math.log(2)); // Using log base 2
        }

        return entropy;
    }

    private List<String> tokenizeCodeSnippet(String codeSnippet) {
        List<String> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();

        for (char c : codeSnippet.toCharArray()) {
            if (Character.isWhitespace(c)) {
                if (!currentToken.isEmpty()) {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0); // Clear StringBuilder
                }
            } else {
                currentToken.append(c);
            }
        }

        // Add the last token if it exists
        if (!currentToken.isEmpty()) {
            tokens.add(currentToken.toString());
        }

        return tokens;
    }


    @Override
    public String getIdentifier() {
        return "TokenEntropy";
    }
}


