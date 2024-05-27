package de.uni_passau.fim.se2.sa.readability.features;


import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.quality.NotNull;
import de.uni_passau.fim.se2.sa.readability.utils.OperandVisitor;
import de.uni_passau.fim.se2.sa.readability.utils.OperatorVisitor;


import java.util.Map;
import java.util.Set;
import java.util.*;

public class HalsteadVolumeFeature extends FeatureMetric {

    /**
     * Computes the Halstead Volume metric based on the given code snippet.
     *
     * @return Halstead Volume of the given code snippet.
     */
    @Override
    public double computeMetric(String codeSnippet) {
        try {
            // Parse the code snippet
            BodyDeclaration<?> bodyDeclaration = parseJavaSnippet(codeSnippet);

            // Visit the code to count operators and operands
            OperatorVisitor operatorVisitor = new OperatorVisitor();
            OperandVisitor operandVisitor = new OperandVisitor();

            bodyDeclaration.accept(operatorVisitor, null);
            bodyDeclaration.accept(operandVisitor, null);

            // Retrieve operator counts
            Map<OperatorVisitor.OperatorType, Integer> operatorCounts = operatorVisitor.getOperatorsPerMethod();
            Set<OperatorVisitor.OperatorType> uniqueOperators = new HashSet<>(operatorCounts.keySet());
            int totalOperators = operatorCounts.values().stream().mapToInt(Integer::intValue).sum();

            // Retrieve operand counts
            Map<String, Integer> operandCounts = operandVisitor.getOperandsPerMethod();
            Set<String> uniqueOperands = new HashSet<>(operandCounts.keySet());
            int totalOperands = operandCounts.values().stream().mapToInt(Integer::intValue).sum();

            System.out.println("total operands" + totalOperands );

            // Compute Halstead metrics
            int N1 = totalOperators;
            int N2 = totalOperands;
            int n1 = uniqueOperators.size();
            int n2 = uniqueOperands.size();
            int N = N1 + N2;
            int n = n1 + n2;



         // Handle edge cases
         if (n == 0) {
            return 0.0;
        }

        // Calculate Halstead Volume
        double volume = N * (Math.log(n) / Math.log(2));

        // Handle negative or infinite volume values
        if (Double.isNaN(volume) || volume < 0) {
            return 0.0;
        }

        return volume;
        } catch ( ParseException e) {
            // Handle parse exceptions if needed
            e.printStackTrace();
            return 0.0;
        }
    }

    @Override
    public String getIdentifier() {
        return "H_VOLUME";
    }

}
