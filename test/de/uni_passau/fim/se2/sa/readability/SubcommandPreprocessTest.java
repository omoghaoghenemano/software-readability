package de.uni_passau.fim.se2.sa.readability;

import de.uni_passau.fim.se2.sa.readability.subcommands.SubcommandPreprocess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import picocli.CommandLine;
import picocli.CommandLine.ParameterException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class SubcommandPreprocessTest {

    private SubcommandPreprocess subcommandPreprocess;
    private CommandLine commandLine;

    @BeforeEach
    void setUp() {
        subcommandPreprocess = new SubcommandPreprocess();
        commandLine = new CommandLine(subcommandPreprocess);
    }

    @Test
    void testSetSourceDirectoryValid(@TempDir Path tempDir) {
        File validDir = tempDir.toFile();
        subcommandPreprocess.setSourceDirectory(validDir);
        assertEquals(validDir.toPath(), subcommandPreprocess.sourceDir);
    }

    @Test
    void testSetSourceDirectoryInvalid() {
        File invalidDir = new File("invalid_directory");
        ParameterException exception = assertThrows(ParameterException.class, () -> subcommandPreprocess.setSourceDirectory(invalidDir));
        assertEquals("Source directory does not exist.", exception.getMessage());
    }

    @Test
    void testSetTruthValid(@TempDir Path tempDir) throws IOException {
        File validFile = tempDir.resolve("truth.csv").toFile();
        try (FileWriter writer = new FileWriter(validFile)) {
            writer.write("Rater,Snippet1,Snippet2\nRater1,4,3\n");
        }
        subcommandPreprocess.setTruth(validFile);
        assertEquals(validFile, subcommandPreprocess.truth);
    }

    @Test
    void testSetTruthInvalid() {
        File invalidFile = new File("invalid_file.csv");
        ParameterException exception = assertThrows(ParameterException.class, () -> subcommandPreprocess.setTruth(invalidFile));
        assertEquals("Truth file does not exist.", exception.getMessage());
    }

    @Test
    void testSetTargetFileValid(@TempDir Path tempDir) {
        File validTargetFile = tempDir.resolve("target.csv").toFile();
        subcommandPreprocess.setTargetFile(validTargetFile);
        assertEquals(validTargetFile, subcommandPreprocess.targetFile);
    }

    @Test
    void testSetTargetFileInvalidDirectory() {
        File invalidTargetFile = new File("invalid_directory/target.csv");
        ParameterException exception = assertThrows(ParameterException.class, () -> subcommandPreprocess.setTargetFile(invalidTargetFile));
        assertEquals("Target directory does not exist.", exception.getMessage());
    }

    @Test
    void testSetTargetFileInvalidExtension(@TempDir Path tempDir) {
        File invalidTargetFile = tempDir.resolve("target.txt").toFile();
        ParameterException exception = assertThrows(ParameterException.class, () -> subcommandPreprocess.setTargetFile(invalidTargetFile));
        assertEquals("Target file must end with a .csv suffix", exception.getMessage());
    }


}
