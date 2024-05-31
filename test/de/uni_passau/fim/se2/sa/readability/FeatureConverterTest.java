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

import static org.junit.jupiter.api.Assertions.*;

class FeatureConverterTest {

    private SubcommandPreprocess subcommandPreprocess;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        subcommandPreprocess = new SubcommandPreprocess();
        subcommandPreprocess.spec = CommandLine.Model.CommandSpec.forAnnotatedObject(subcommandPreprocess);
    }

    @Test
    void testNonExistentSourceDirectory() {
        File nonExistentDir = tempDir.resolve("nonExistentDir").toFile();
        NullPointerException exception = assertThrows(NullPointerException.class, () ->
                subcommandPreprocess.setSourceDirectory(nonExistentDir));
        assertEquals("commandLine", exception.getMessage());
    }

    @Test
    void testNonExistentTruthFile() {
        File nonExistentFile = tempDir.resolve("nonExistentFile.csv").toFile();
        NullPointerException exception = assertThrows(NullPointerException.class, () ->
                subcommandPreprocess.setTruth(nonExistentFile));
        assertEquals("commandLine", exception.getMessage());
    }





    @Test
    void testGenerateCSVHeader() {
        StringBuilder csv = new StringBuilder();
        List<FeatureMetric> featureMetrics = List.of(new NumberLinesFeature(), new TokenEntropyFeature(), new HalsteadVolumeFeature());
        SubcommandPreprocess.generateCSVHeader(csv, featureMetrics);
        String expectedHeader = "File,NumberLines,TOKEN_ENTROPY,H_VOLUME,Truth\n";
        assertEquals(expectedHeader, csv.toString());
    }

    @Test
    void testCollectCSVBody() throws IOException {
        File sourceDir = tempDir.resolve("source").toFile();
        File snippetFile1 = new File(sourceDir, "1.jsnp");
        File snippetFile2 = new File(sourceDir, "2.jsnp");

        assertTrue(sourceDir.mkdir());
        try (BufferedWriter writer = Files.newWriter(snippetFile1, Charsets.UTF_8)) {
            writer.write("public class Test1 {}");
        }
        try (BufferedWriter writer = Files.newWriter(snippetFile2, Charsets.UTF_8)) {
            writer.write("public class Test2 {}");
        }

        File truthFile = tempDir.resolve("/Users/mano/software-analysis/ss24softwareanalysereadability-omogha01/resources/target.csv").toFile();
        try (BufferedWriter writer = Files.newWriter(truthFile, Charsets.UTF_8)) {
            writer.write("Rater,1,2\nMean,4.0,2.5\n");
        }

        subcommandPreprocess.setSourceDirectory(sourceDir);
        subcommandPreprocess.setTruth(truthFile);

        StringBuilder csv = new StringBuilder();
        List<FeatureMetric> featureMetrics = List.of(new NumberLinesFeature(), new TokenEntropyFeature(), new HalsteadVolumeFeature());
        subcommandPreprocess.collectCSVBody(csv, featureMetrics);

        String expectedBody = "1.jsnp,1.00,2.41,.00,Y\n2.jsnp,1.00,2.41,.00,N\n";
        assertEquals(expectedBody, csv.toString());
    }

    @Test
    void testWriteCSVToFile() throws IOException {
        File targetFile = tempDir.resolve("target.csv").toFile();
        subcommandPreprocess.setTargetFile(targetFile);

        String csvContent = "File,NumberLines,TokenEntropy,HalsteadVolume,Truth\n1.jsnp,1.00,0.00,0.00,Y\n";
        subcommandPreprocess.writeCSVToFile(csvContent);

        String writtenContent = Files.asCharSource(targetFile, Charsets.UTF_8).read();
        assertEquals(csvContent, writtenContent);
    }
}
