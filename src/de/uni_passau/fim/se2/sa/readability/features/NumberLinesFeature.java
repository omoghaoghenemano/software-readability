package de.uni_passau.fim.se2.sa.readability.features;

public class NumberLinesFeature extends FeatureMetric {

    /**
     * Computes the number of lines of the given code snippet.
     * Since we are interested in determining the readability of a code snippet, this also includes comments.
     *
     * @return source code lines of the given code snippet.
     */
    @Override
    public double computeMetric(String codeSnippet){
        try{
            int lineCount = 0;
            String[] lines = codeSnippet.split("\\r?\\n");
            //count the number of line count
            lineCount = lines.length;

            return lineCount;
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public String getIdentifier() {
        return "NumberLines";
    }
}
