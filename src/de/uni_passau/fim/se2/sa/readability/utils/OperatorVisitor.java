package de.uni_passau.fim.se2.sa.readability.utils;

import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.*;
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

    public Map<OperatorType, Integer> getOperatorsPerMethod() {
        return operatorsPerMethod;
    }

    @Override
    public void visit(BinaryExpr n, Void arg) {
        super.visit(n, arg);
        operatorsPerMethod.put(OperatorType.BINARY, operatorsPerMethod.getOrDefault(OperatorType.BINARY, 0) + 1);
    }

    @Override
    public void visit(AssignExpr n, Void arg) {
        super.visit(n, arg);
        operatorsPerMethod.put(OperatorType.ASSIGNMENT, operatorsPerMethod.getOrDefault(OperatorType.ASSIGNMENT, 0) + 1);
    }

    @Override
    public void visit(VariableDeclarator n, Void arg) {
        super.visit(n, arg);
        operatorsPerMethod.put(OperatorType.ASSIGNMENT, operatorsPerMethod.getOrDefault(OperatorType.ASSIGNMENT, 0) + 1);
    }

    @Override
    public void visit(ConditionalExpr n, Void arg) {
        super.visit(n, arg);
        operatorsPerMethod.put(OperatorType.CONDITIONAL, operatorsPerMethod.getOrDefault(OperatorType.CONDITIONAL, 0) + 1
        );
    }

    @Override
    public void visit(InstanceOfExpr n, Void arg) {
        super.visit(n, arg);
        operatorsPerMethod.put(OperatorType.TYPE_COMPARISON, operatorsPerMethod.getOrDefault(OperatorType.TYPE_COMPARISON, 0) + 1);
    }

    @Override
    public void visit(UnaryExpr n, Void arg) {
        super.visit(n, arg);
        operatorsPerMethod.put(OperatorType.UNARY, operatorsPerMethod.getOrDefault(OperatorType.UNARY, 0) + 1);
    }
 


}
