package de.uni_passau.fim.se2.sa.readability;

import de.uni_passau.fim.se2.sa.readability.features.FeatureMetric;
import de.uni_passau.fim.se2.sa.readability.features.HalsteadVolumeFeature;
import de.uni_passau.fim.se2.sa.readability.features.NumberLinesFeature;
import de.uni_passau.fim.se2.sa.readability.features.TokenEntropyFeature;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FeatureMetricTest {
    String codeSnippet = "@Override\n" +
            "    public void runTest(final Test test, final TestResult result) {\n" +
            "        Thread t = new Thread() {\n" +
            "            @Override\n" +
            "            public void run() {\n" +
            "                try {\n" +
            "                    // inlined due to limitation in VA/Java\n" +
            "                    //ActiveTestSuite.super.runTest(test, result);\n" +
            "                    test.run(result);\n" +
            "                } finally {\n" +
            "                    ActiveTestSuite.this.runFinished();\n" +
            "                }\n" +
            "            }\n" +
            "        };\n" +
            "        t.start();\n" +
            "    }";

    @Test
    void testNumberLinesFeature() {
        FeatureMetric metric = new NumberLinesFeature();

        assertEquals(16, metric.computeMetric(codeSnippet), 0.01);
    }

    @Test
    void testTokenEntropyFeature() {
        FeatureMetric metric = new TokenEntropyFeature();

        assertTrue(metric.computeMetric(codeSnippet) > 0, "Token entropy should be greater than 0");
    }

    @Test
    void testHalsteadVolumeFeature() {
        FeatureMetric metric = new HalsteadVolumeFeature();

        assertTrue(metric.computeMetric(codeSnippet) > 0, "Halstead volume should be greater than 0");
    }


}
