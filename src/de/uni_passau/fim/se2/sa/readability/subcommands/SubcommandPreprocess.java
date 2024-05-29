package de.uni_passau.fim.se2.sa.readability.subcommands;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import de.uni_passau.fim.se2.sa.readability.features.FeatureMetric;
import de.uni_passau.fim.se2.sa.readability.features.HalsteadVolumeFeature;
import de.uni_passau.fim.se2.sa.readability.features.NumberLinesFeature;
import de.uni_passau.fim.se2.sa.readability.features.TokenEntropyFeature;
import picocli.CommandLine.*;
import picocli.CommandLine.Model.CommandSpec;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.*;

@Command(
        name = "preprocess",
        description = "Preprocesses a directory of java snippet files based on the specified feature metrics"
)
public class SubcommandPreprocess implements Callable<Integer> {

    @Spec
    CommandSpec spec;

    private static final double TRUTH_THRESHOLD = 3.6;

    private Path sourceDir;
    private File truth;
    private File targetFile;

    @Option(
            names = {"-s", "--source"},
            description = "The directory containing java snippet (.jsnp) files",
            required = true
    )
    public void setSourceDirectory(final File sourceDir) {
        if (!sourceDir.exists() || !sourceDir.isDirectory()) {
            throw new ParameterException(spec.commandLine(), "Source directory does not exist.");
        }
        this.sourceDir = sourceDir.toPath();
    }

    @Option(
            names = {"-g", "--ground-truth"},
            description = "The ground truth csv file containing the human readability ratings of the code snippets",
            required = true
    )
    public void setTruth(final File truth) {
        if (!truth.exists() || !truth.isFile()) {
            throw new ParameterException(spec.commandLine(), "Truth file does not exist.");
        }
        this.truth = truth;
    }

    @Option(
            names = {"-t", "--target"},
            description = {"The target file where the preprocessed data will be saved"},
            required = true
    )
    public void setTargetFile(final File targetFile) {
        if (!targetFile.getParentFile().isDirectory()) {
            throw new ParameterException(spec.commandLine(), "Target directory does not exist.");
        }
        if (!Files.getFileExtension(targetFile.getName()).equals("csv")) {
            throw new ParameterException(spec.commandLine(), "Target file must end with a .csv suffix");
        }
        this.targetFile = targetFile;
    }

    @Parameters(
            paramLabel = "featureMetrics",
            description = "The The feature metrics to be used: [LINES, TOKEN_ENTROPY, H_VOLUME]",
            arity = "1...",
            converter = FeatureConverter.class
    )
    private List<FeatureMetric> featureMetrics;


    public Integer call() {

        StringBuilder csv = new StringBuilder();
        generateCSVHeader(csv, featureMetrics);
        collectCSVBody(csv, featureMetrics);

        writeCSVToFile(csv.toString());

        System.out.println(csv);
        return 0;
    }

    private String readFileSpinnet(File snippetFile) {
        try {
            return Files.asCharSource(snippetFile, Charsets.UTF_8).read();
        } catch (IOException e) {
            System.err.println("Error reading snippet file: " + e.getMessage());
            return "";
        }
    }

    private List<Double> computeFeatureValues(String codeSnippet, List<FeatureMetric> featureMetrics) {
            double numberLines =  new NumberLinesFeature().computeMetric(codeSnippet);
            double tokenEntropy =   new TokenEntropyFeature().computeMetric(codeSnippet);
            double halsteadVolume = new HalsteadVolumeFeature().computeMetric(codeSnippet);
        
            return Arrays.asList(numberLines, tokenEntropy, halsteadVolume);
        
    }

    private Map<String, Double> loadTruthScores() {
    Map<String, Double> truthMap = new TreeMap<>(new TruthMapComparator());

    try (BufferedReader reader = Files.newReader(truth, StandardCharsets.UTF_8)) {
        String line;
        boolean isFirstLine = true;

        while ((line = reader.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }

            String[] parts = line.split(",");
            String rater = parts[0].trim();

            for (int i = 1; i < parts.length; i++) {
                try {
                    double score = Double.parseDouble(parts[i].trim());
                    truthMap.put(rater + "-" + i, score);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid score format: " + parts[i] + " in line: " + line);
                }
            }
        }
    } catch (IOException e) {
        System.err.println("Error reading truth file: " + e.getMessage());
    }

    return truthMap;
}

private static class TruthMapComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] parts1 = o1.split("-");
        String[] parts2 = o2.split("-");
        int raterComparison = parts1[0].compareTo(parts2[0]);
        if (raterComparison != 0) {
            return raterComparison;
        }
        Integer snippet1 = Integer.parseInt(parts1[1]);
        Integer snippet2 = Integer.parseInt(parts2[1]);
        return snippet1.compareTo(snippet2);
    }
}   


   private String computeTruthValue( double meanScore, double TRUTH_THRESHOLD){
    String value;
    if(meanScore > TRUTH_THRESHOLD){
        value = "Y";
    }
    else{
        value = "N";
    }

    return value;
   }



    /**
     * Generates the csv header represented by [SnippetFile, feature1, feature2, ...]
     *
     * @param csv            the builder for the csv.
     * @param featureMetrics the list of specified features via the cli.
     */
    private static void generateCSVHeader(StringBuilder csv, List<FeatureMetric> featureMetrics) {
        csv.append("File");
        for (FeatureMetric featureMetric : featureMetrics) {
            csv.append(String.format(",%s", featureMetric.getIdentifier()));
        }
        csv.append(",Truth");
        csv.append(System.lineSeparator());
    }
    

    /**
     * Traverses through each java snippet in the specified source directory and computes the specified list of feature metrics.
     * Each snippet is then saved together with its extracted feature values and the truth score as one row in the csv, resulting
     * in the scheme [File,NumberLines,TokenEntropy,HalsteadVolume,Truth].
     * <p>
     * The File column value corresponds to the respective file name.
     * All feature values are rounded to two decimal places.
     * The truth value corresponds to a String that is set to the value "Y" if the mean rater score of a given snippet is greater or equal
     * than the TRUTH_THRESHOLD. Otherwise, if the mean score is lower than the TRUTH_THRESHOLD the truth value String is set to "N".
     *
     * @param csv            the builder for the csv.
     * @param featureMetrics the list of specified features via the cli.
     */
    private void collectCSVBody(StringBuilder csv, List<FeatureMetric> featureMetrics) {
        Map<String, Double> truthMap = loadTruthScores();

        File[] snippetFiles = sourceDir.toFile().listFiles((dir, name) -> name.endsWith(".jsnp"));
        
        // Checks if snippetFiles is not null and sort the files
        if (snippetFiles != null) {
            Arrays.sort(snippetFiles, new Comparator<File>() {
                @Override
                public int compare(File f1, File f2) {
                    String firstName = f1.getName();
                    String secondName = f2.getName();
        
                    return Integer.compare(Integer.parseInt(firstName.substring(0, firstName.indexOf('.'))), Integer.parseInt(secondName.substring(0, secondName.indexOf('.'))));
                }
            });
        
            //initialize  decimal formating symbol
             DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat df = new DecimalFormat("#.00", symbols);
            for (File snippetFile : snippetFiles) {
                String getFileName = snippetFile.getName();
                String codeSnippet = readFileSpinnet(snippetFile);
                List<Double> featureValues = computeFeatureValues(codeSnippet, featureMetrics);
                csv.append(getFileName);

                for (Double value : featureValues) {
        
                    String valueFormatted = df.format(value);
                    System.out.println(valueFormatted);
        
                    csv.append(",").append(valueFormatted);
                }
        
                // get snippet identifier based on file 
                String codeSnippetIdentifier= getFileName.replace(".jsnp", "");
        
                
                double meanScore = 0.0;
                for (Map.Entry<String, Double> entry : truthMap.entrySet()) {
                    if (entry.getKey().endsWith("-" + codeSnippetIdentifier)) {
                        meanScore = entry.getValue();
                        break;
                    }
                }
               
                String truthValue = computeTruthValue(meanScore, TRUTH_THRESHOLD);
                csv.append(",").append(truthValue);
                csv.append(System.lineSeparator());
    
            }
        } else {
            System.out.println("No snippet files found.");
        }
        
    }

    /**
     * Writes the generated csv String to the target file.
     *
     * @param csv the generated csv String
     */
    private void writeCSVToFile(String csv) {
        try (BufferedWriter writer = Files.newWriter(new File(targetFile.getAbsolutePath()), Charsets.UTF_8)) {
            writer.write(csv);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}

/**
 * Converts supplied cli parameters to the respective {@link FeatureMetric}.
 */
class FeatureConverter implements ITypeConverter<FeatureMetric> {
    @Override
    public FeatureMetric convert(String metric) {
        return switch (metric.toLowerCase()) {
            case "lines" -> new NumberLinesFeature();
            case "h_volume" -> new HalsteadVolumeFeature();
            case "token_entropy" -> new TokenEntropyFeature();
            default -> throw new IllegalArgumentException("The metric '" + metric + "' is not a valid option.");
        };
    }
}
