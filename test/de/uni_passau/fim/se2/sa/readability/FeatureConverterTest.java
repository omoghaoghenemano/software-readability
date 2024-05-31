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

    @Test
    void testGenerateCSVHeader() {
        StringBuilder csv = new StringBuilder();
        List<FeatureMetric> featureMetrics = List.of(new NumberLinesFeature(), new TokenEntropyFeature(), new HalsteadVolumeFeature());
        SubcommandPreprocess.generateCSVHeader(csv, featureMetrics);
        String expectedHeader = "File,NumberLines,TOKEN_ENTROPY,H_VOLUME,Truth\n";
        assertEquals(expectedHeader, csv.toString());
    }


}
