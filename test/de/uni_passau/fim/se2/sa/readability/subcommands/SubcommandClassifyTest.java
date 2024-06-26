package de.uni_passau.fim.se2.sa.readability.subcommands;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import picocli.CommandLine;
import picocli.CommandLine.ParameterException;
import weka.classifiers.Evaluation;
import weka.core.DenseInstance;
import weka.core.Instances;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class SubcommandClassifyTest {
    @TempDir
    Path tempDir;
    private SubcommandClassify subcommandClassify;
    private CommandLine commandLine;

    @BeforeEach
    void setUp() {
        subcommandClassify = new SubcommandClassify();
        commandLine = new CommandLine(subcommandClassify);
    }



    @Test
    void testSetDataFileInvalid(@TempDir Path tempDir) {
        File invalidFile = tempDir.resolve("data.csv").toFile();

        ParameterException exception = assertThrows(ParameterException.class, () -> subcommandClassify.setDataFile(invalidFile));
        assertEquals("The data file does not exist or is not a file.", exception.getMessage());
    }

    @Test
    void testCallWithValidData(@TempDir Path tempDir) throws IOException {
        File validFile = tempDir.resolve("data.csv").toFile();
        try (FileWriter writer = new FileWriter(validFile)) {
            writer.write("File,NumberLines,TOKEN_ENTROPY,H_VOLUME,Truth\n1.jsnp,16.00,2.03,62.27,Y");
        }
        subcommandClassify.setDataFile(validFile);

        int result = subcommandClassify.call();
        assertEquals(1, result);
    }


}
