package de.uni_passau.fim.se2.sa.readability.subcommands;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import de.uni_passau.fim.se2.sa.readability.features.FeatureMetric;
import de.uni_passau.fim.se2.sa.readability.features.HalsteadVolumeFeature;
import de.uni_passau.fim.se2.sa.readability.features.NumberLinesFeature;
import de.uni_passau.fim.se2.sa.readability.features.TokenEntropyFeature;
import picocli.CommandLine.*;
import picocli.CommandLine.Model.CommandSpec;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Callable;

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
        throw new UnsupportedOperationException("Implement me");
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
