package de.uni_passau.fim.se2.sa.readability.utils;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperandVisitorTest {
    private OperandVisitor operandVisitor;

    @BeforeEach
    public void setUp() {
        operandVisitor = new OperandVisitor();
    }


    @Test
    public void testVisitNullLiteral() {
        String code = "public class Test { void test() { Object obj = null; } }";
        parseAndAccept(code, operandVisitor);

        Map<String, Integer> operands = operandVisitor.getOperandsPerMethod();
        assertEquals(1, operands.get("null"));
        assertEquals(1, operands.get("Object"));
        assertEquals(1, operands.get("obj"));
    }

    @Test
    public void testVisitParameter() {
        String code = "public class Test { void test(int x, String y) { int z = x + y.length(); } }";
        parseAndAccept(code, operandVisitor);

        Map<String, Integer> operands = operandVisitor.getOperandsPerMethod();
        assertEquals(3, operands.get("x"));

    }
    private void parseAndAccept(String code, VoidVisitor<Void> visitor) {
        JavaParser javaParser = new JavaParser();
        ParseResult<CompilationUnit> result = javaParser.parse(code);
        if (result.isSuccessful()) {
            CompilationUnit cu = result.getResult().get();
            cu.accept(visitor, null);
        } else {
            // Handle parsing errors if needed
            System.err.println("Parsing failed: " + result.getProblems());
        }
    }

}
