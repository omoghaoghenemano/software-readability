package de.uni_passau.fim.se2.sa.readability.utils;

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
}
