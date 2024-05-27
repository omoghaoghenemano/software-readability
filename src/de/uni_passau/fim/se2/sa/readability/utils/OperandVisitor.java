package de.uni_passau.fim.se2.sa.readability.utils;

import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.HashMap;
import java.util.Map;

public class OperandVisitor extends VoidVisitorAdapter<Void> {


    private final Map<String, Integer> operandsPerMethod;

    public OperandVisitor() {
        operandsPerMethod = new HashMap<>();
    }
    @Override
    public void visit(NameExpr n, Void arg) {
        operandsPerMethod.put(n.getNameAsString(), operandsPerMethod.getOrDefault(n.getNameAsString(), 0) + 1);
        super.visit(n, arg);
    }

    @Override
    public void visit(Parameter n, Void arg) {
        operandsPerMethod.put(n.getNameAsString(), operandsPerMethod.getOrDefault(n.getNameAsString(), 0) + 1);
        super.visit(n, arg);
    }

    public Map<String, Integer> getOperandsPerMethod() {
        return operandsPerMethod;
    }
}
