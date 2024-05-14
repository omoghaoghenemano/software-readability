package de.uni_passau.fim.se2.sa.readability.features;

import com.github.javaparser.*;
import com.github.javaparser.ast.body.BodyDeclaration;

public abstract class FeatureMetric {

    /**
     * Computes the metric of the respective feature.
     *
     * @return feature metric value.
     */
    public abstract double computeMetric(String codeSnippet);

    /**
     * Returns a unique identifier for the concrete FeatureMetric.
     *
     * @return unique FeatureMetric identifier.
     */
    public abstract String getIdentifier();

    /**
     * Parses the code of a java snippet .jsnp file using the JavaParser library such that it can accept JavaParser visitors.
     *
     * @param codeSnippet The code of the respective .jsnp as a String
     * @return The parsed code snippet ready to accept JavaParser visitors.
     * @throws ParseException if the code snippet could not be parsed by the JavaParser library.
     */
    protected BodyDeclaration<?> parseJavaSnippet(String codeSnippet) throws ParseException {
        ParseResult<BodyDeclaration<?>> parseResult = new JavaParser().parse(ParseStart.CLASS_BODY, new StringProvider(codeSnippet));

        if (!parseResult.isSuccessful() || parseResult.getResult().isEmpty()) {
            throw new ParseException("Could not parse " + codeSnippet);
        }

        return parseResult.getResult().get();
    }
}
