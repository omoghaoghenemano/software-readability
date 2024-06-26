package de.uni_passau.fim.se2.sa.readability.features;

import com.github.javaparser.ParseException;
import com.github.javaparser.ParseProblemException;
import com.github.javaparser.ast.body.BodyDeclaration;
import de.uni_passau.fim.se2.sa.readability.utils.OperatorVisitor;
import de.uni_passau.fim.se2.sa.readability.utils.OperandVisitor;
import java.util.Map;

public class HalsteadVolumeFeature extends FeatureMetric {

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

            // Retrieve operator and operand counts
            Map<OperatorVisitor.OperatorType, Integer> operatorsPerMethod = operatorVisitor.getOperatorsPerMethod();
            Map<String, Integer> operandsPerMethod = operandVisitor.getOperandsPerMethod();

            // Calculate the total counts
            int N1 = operatorsPerMethod.values().stream().mapToInt(Integer::intValue).sum();
            int N2 = operandsPerMethod.values().stream().mapToInt(Integer::intValue).sum();

            //print n1
            System.out.println("operand:"+N1 + " " + "operator" + N2);


            // Calculate the unique counts
            int n1 = operatorsPerMethod.size();
            int n2 = operandsPerMethod.size();

            // Calculate program length (N) and program vocabulary (n)
            int N = N1 + N2;
            int n = n1 + n2;

     

            // Ensure n is not zero to avoid logarithmic issues
            if (n == 0) {
                return 0.0;
            }

            // Calculate Halstead Volume
            double volume = N * (Math.log(n) / Math.log(2));

            return volume;
        } catch (ParseProblemException | ParseException e) {
            // Handle parse exceptions if needed
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
            return 0.0;
        }
    }

    @Override
    public String getIdentifier() {
        return "H_VOLUME";
    }

}
