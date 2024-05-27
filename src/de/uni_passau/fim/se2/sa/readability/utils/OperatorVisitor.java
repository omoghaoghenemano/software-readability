package de.uni_passau.fim.se2.sa.readability.utils;

import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.HashMap;
import java.util.Map;

public class OperatorVisitor extends VoidVisitorAdapter<Void> {

    public enum OperatorType {
        ASSIGNMENT,         // x=y
        BINARY,             // x+y
        UNARY,              // -x, ++x
        CONDITIONAL,        // ==, <=, &&, ||
        TYPE_COMPARISON,    // instanceof
    }

    private final Map<OperatorType, Integer> operatorsPerMethod;

    public OperatorVisitor() {
        operatorsPerMethod = new HashMap<>();
    }
    @Override
    public void visit(BinaryExpr n, Void arg) {
        operatorsPerMethod.put(OperatorType.BINARY, operatorsPerMethod.getOrDefault(OperatorType.BINARY, 0) + 1);
        super.visit(n, arg);
    }

    @Override
    public void visit(AssignExpr n, Void arg) {
        operatorsPerMethod.put(OperatorType.ASSIGNMENT, operatorsPerMethod.getOrDefault(OperatorType.ASSIGNMENT, 0) + 1);
        super.visit(n, arg);
    }

    public Map<OperatorType, Integer> getOperatorsPerMethod() {
        return operatorsPerMethod;
    }
}
