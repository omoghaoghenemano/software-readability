package de.uni_passau.fim.se2.sa.readability.features;


import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.quality.NotNull;
import de.uni_passau.fim.se2.sa.readability.utils.OperandVisitor;
import de.uni_passau.fim.se2.sa.readability.utils.OperatorVisitor;

import java.util.Map;

public class HalsteadVolumeFeature extends FeatureMetric {

    /**
     * Computes the Halstead Volume metric based on the given code snippet.
     *
     * @return Halstead Volume of the given code snippet.
     */
    @Override
    public double computeMetric(String codeSnippet) {
        OperandVisitor operandVisitor = new OperandVisitor();
        OperatorVisitor operatorVisitor = new OperatorVisitor();

        JavaParser parser = new JavaParser();
        CompilationUnit compilationUnit = parser.parse(codeSnippet).getResult().orElseThrow(() -> new IllegalArgumentException("Invalid code snippet"));
        compilationUnit.accept(operandVisitor, null);
        compilationUnit.accept(operatorVisitor, null);

        Map<String, Integer> operandsPerMethod = operandVisitor.getOperandsPerMethod();
        Map<OperatorVisitor.OperatorType, Integer> operatorsPerMethod = operatorVisitor.getOperatorsPerMethod();

        int n1 = operatorsPerMethod.size();
        int n2 = operandsPerMethod.size();
        int N1 = operatorsPerMethod.values().stream().mapToInt(Integer::intValue).sum();
        int N2 = operandsPerMethod.values().stream().mapToInt(Integer::intValue).sum();

        int n = n1 + n2;
        int N = N1 + N2;

        if (n == 0) {
            throw new IllegalArgumentException("Code snippet does not contain valid operators or operands");
        }

        // Calculate Halstead Volume
        double volume = N * (Math.log(n) / Math.log(2));
        return volume;
    }


    @Override
    public String getIdentifier() {
        return "HalsteadVolume";
    }
}
