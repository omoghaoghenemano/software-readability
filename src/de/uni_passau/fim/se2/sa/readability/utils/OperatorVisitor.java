package de.uni_passau.fim.se2.sa.readability.utils;

import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.expr.InstanceOfExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.HashMap;
import java.util.Map;

public class OperatorVisitor extends VoidVisitorAdapter<Void> {

    public enum OperatorType {
        ASSIGNMENT,         // x=y
        BINARY,             // x+y
        UNARY,              // -x, ++x
        CONDITIONAL,        // ==, <=, &&, ||
        TYPE_COMPARISON    // instanceof
    }

    private final Map<OperatorType, Integer> operatorsPerMethod;

    public OperatorVisitor() {
        operatorsPerMethod = new HashMap<>();
        // Initialize the counts for each operator type
        for (OperatorType type : OperatorType.values()) {
            operatorsPerMethod.put(type, 0);
        }
    }

    @Override
    public void visit(BinaryExpr n, Void arg) {
        super.visit(n, arg);
        operatorsPerMethod.put(OperatorType.BINARY, operatorsPerMethod.get(OperatorType.BINARY) + 1);
    }

    @Override
    public void visit(AssignExpr n, Void arg) {
        super.visit(n, arg);
        operatorsPerMethod.put(OperatorType.ASSIGNMENT, operatorsPerMethod.get(OperatorType.ASSIGNMENT) + 1);
    }

    @Override
    public void visit(UnaryExpr n, Void arg) {
        super.visit(n, arg);
        operatorsPerMethod.put(OperatorType.UNARY, operatorsPerMethod.get(OperatorType.UNARY) + 1);
    }

    @Override
    public void visit(ConditionalExpr n, Void arg) {
        super.visit(n, arg);
        operatorsPerMethod.put(OperatorType.CONDITIONAL, operatorsPerMethod.get(OperatorType.CONDITIONAL) + 1);
    }

    @Override
    public void visit(InstanceOfExpr n, Void arg) {
        super.visit(n, arg);
        operatorsPerMethod.put(OperatorType.TYPE_COMPARISON, operatorsPerMethod.get(OperatorType.TYPE_COMPARISON) + 1);
    }

    public Map<OperatorType, Integer> getOperatorsPerMethod() {
        return operatorsPerMethod;
    }
}
