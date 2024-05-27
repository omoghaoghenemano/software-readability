package de.uni_passau.fim.se2.sa.readability.features;

import java.io.IOException;
import java.util.*;
import com.github.javaparser.JavaParser;
import com.github.javaparser.JavaToken;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ParseProblemException;
import com.github.javaparser.TokenRange;


import java.util.HashMap;
import java.util.Map;

public class TokenEntropyFeature extends FeatureMetric {

    /**
     * Computes the entropy metric based on the tokens of the given code snippet.
     * Since we are interested in the readability of code as perceived by a human, tokens also include whitespaces and the like.
     *
     * @return token entropy of the given code snippet.
     */
  
  

        @Override
        public double computeMetric(String codeSnippet) {
            try {
                // Parse the code snippet
                BodyDeclaration<?> bodyDeclaration = parseJavaSnippet(codeSnippet);
    
                // Extract tokens and calculate the frequency of each token
                Map<String, Integer> tokenFrequency = new HashMap<>();
                int totalTokens = 0;
    
                if (bodyDeclaration.getTokenRange().isPresent()) {
                    TokenRange tokenRange = bodyDeclaration.getTokenRange().get();
                    for (JavaToken token : tokenRange) {
                        String tokenText = token.getText();
                        tokenFrequency.put(tokenText, tokenFrequency.getOrDefault(tokenText, 0) + 1);
                        totalTokens++;
                    }
                }
    
                // Calculate the Shannon Entropy
                double entropy = 0.0;
                for (int count : tokenFrequency.values()) {
                    double probability = (double) count / totalTokens;
                    entropy -= probability * (Math.log(probability) / Math.log(2));
                }
    
                return entropy;
            } catch (  ParseException e) {
                // Handle parse exceptions if needed
                e.printStackTrace();
                return 0.0;
            }
        }
    
        @Override
        public String getIdentifier() {
            return "TOKEN_ENTROPY";
        }
    }