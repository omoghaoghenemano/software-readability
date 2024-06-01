package de.uni_passau.fim.se2.sa.readability;



import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitor;
import de.uni_passau.fim.se2.sa.readability.utils.OperatorVisitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OperatorVisitorTest {

    private OperatorVisitor operatorVisitor;

    @BeforeEach
    public void setUp() {
        operatorVisitor = new OperatorVisitor();
    }

    @Test
    public void testOperatorVisitor() {
        String code = "public class TestClass {" +
                "    public void testMethod() {" +
                "        int x = 1 + 2;" +  // BINARY, ASSIGNMENT
                "        x += 3;" +        // ASSIGNMENT
                "        if (x == 4) {}" + // CONDITIONAL
                "        x++;" +           // UNARY
                "        boolean y = x instanceof Integer;" + // TYPE_COMPARISON, ASSIGNMENT
                "    }" +
                "}";

        JavaParser javaParser = new JavaParser();
        ParseResult<CompilationUnit> result = javaParser.parse(code);

        assertTrue(result.isSuccessful(), "Parsing was not successful");

        CompilationUnit cu = result.getResult().orElseThrow(() -> new AssertionError("No CompilationUnit present"));

        VoidVisitor<Void> visitor = operatorVisitor;
        visitor.visit(cu, null);

        Map<OperatorVisitor.OperatorType, Integer> operatorsCount = operatorVisitor.getOperatorsPerMethod();

        System.out.println("Operator Counts: " + operatorsCount); // Debug output

        assertEquals(3, (int) operatorsCount.getOrDefault(OperatorVisitor.OperatorType.ASSIGNMENT, 0));
        assertEquals(2, (int) operatorsCount.getOrDefault(OperatorVisitor.OperatorType.BINARY, 0));
        assertEquals(0, (int) operatorsCount.getOrDefault(OperatorVisitor.OperatorType.CONDITIONAL, 0));
        assertEquals(1, (int) operatorsCount.getOrDefault(OperatorVisitor.OperatorType.UNARY, 0));
        assertEquals(1, (int) operatorsCount.getOrDefault(OperatorVisitor.OperatorType.TYPE_COMPARISON, 0));
    }
}
