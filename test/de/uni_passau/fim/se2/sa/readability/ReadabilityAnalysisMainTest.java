package de.uni_passau.fim.se2.sa.readability;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ReadabilityAnalysisMainTest {

    @Test
    public void testNoSubcommand() {
        // Prepare to capture the system output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Simulate running the main class with no arguments
        String[] args = {};
        int exitCode = new CommandLine(new ReadabilityAnalysisMain()).execute(args);

        // Assert the expected output and exit code
        assertEquals(0, exitCode);
        assertTrue(outContent.toString().contains("Subcommand needed: 'preprocess' or 'classify'"));

        // Restore the original System.out
        System.setOut(System.out);
    }


}
