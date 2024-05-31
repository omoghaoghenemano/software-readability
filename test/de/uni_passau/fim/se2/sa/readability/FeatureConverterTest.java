package de.uni_passau.fim.se2.sa.readability;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import de.uni_passau.fim.se2.sa.readability.features.FeatureMetric;
import de.uni_passau.fim.se2.sa.readability.features.HalsteadVolumeFeature;
import de.uni_passau.fim.se2.sa.readability.features.NumberLinesFeature;
import de.uni_passau.fim.se2.sa.readability.features.TokenEntropyFeature;
import de.uni_passau.fim.se2.sa.readability.subcommands.SubcommandPreprocess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import picocli.CommandLine;
import picocli.CommandLine.ParameterException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FeatureConverterTest {

    @TempDir
    Path tempDir;


    @Test
    void testGenerateCSVHeader() {
        StringBuilder csv = new StringBuilder();
        List<FeatureMetric> featureMetrics = List.of(new NumberLinesFeature(), new TokenEntropyFeature(), new HalsteadVolumeFeature());
        SubcommandPreprocess.generateCSVHeader(csv, featureMetrics);
        String expectedHeader = "File,NumberLines,TOKEN_ENTROPY,H_VOLUME,Truth\n";
        assertEquals(expectedHeader, csv.toString());
    }

    @Test
    public void testComputeTruthValue() {
        SubcommandPreprocess subcommand = new SubcommandPreprocess();
        assertEquals("Y", subcommand.computeTruthValue(4.0, 3.6));
        assertEquals("N", subcommand.computeTruthValue(3.0, 3.6));
    }

    @Test
    public void testComputeFeatureValues() {
        SubcommandPreprocess subcommand = new SubcommandPreprocess();
        String codeSnippet = "public class Test { }";
        List<FeatureMetric> featureMetrics = new ArrayList<>();
        featureMetrics.add(new NumberLinesFeature());
        featureMetrics.add(new HalsteadVolumeFeature());
        featureMetrics.add(new TokenEntropyFeature());

        List<Double> featureValues = subcommand.computeFeatureValues(codeSnippet, featureMetrics);

        assertEquals(3, featureValues.size());
        // Assert based on expected values from the features, e.g., using known inputs/outputs
        // Add more specific assertions based on your knowledge of the features
    }

    @Test
    public void testSetSourceDirectory_WhenDirectoryExists() {
        SubcommandPreprocess subcommand = new SubcommandPreprocess();
        CommandLine commandLine = new CommandLine(subcommand);
        subcommand.spec = commandLine.getCommandSpec();
        File sourceDir = new File("existing_directory");
        sourceDir.mkdir();

        subcommand.setSourceDirectory(sourceDir);

        assertEquals(Path.of("existing_directory"), subcommand.sourceDir);
    }



}
