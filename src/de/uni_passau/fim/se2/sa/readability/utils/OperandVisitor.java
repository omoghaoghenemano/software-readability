package de.uni_passau.fim.se2.sa.readability.utils;


import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.body.Parameter;

import java.util.*;

public class OperandVisitor extends VoidVisitorAdapter<Void> {
    private final Map<String, Integer> operandsPerMethod;
  

    public OperandVisitor() {
        operandsPerMethod = new HashMap<>();
    }

    public Map<String, Integer> getOperandsPerMethod() {
        return operandsPerMethod;
    }


    @Override
    public void visit(SimpleName n, Void arg) {
        super.visit(n, arg);
        String name = n.getIdentifier();
        operandsPerMethod.put(name, operandsPerMethod.getOrDefault(name, 0) + 1);
    }

    @Override
    public void visit(BooleanLiteralExpr n, Void arg) {
        super.visit(n, arg);
        String value = String.valueOf(n.getValue());
        operandsPerMethod.put(value, operandsPerMethod.getOrDefault(value, 0) + 1);
    }

    @Override
    public void visit(CharLiteralExpr n, Void arg) {
        super.visit(n, arg);
        String value = n.getValue();
        operandsPerMethod.put(value, operandsPerMethod.getOrDefault(value, 0) + 1);
    }

    @Override
    public void visit(DoubleLiteralExpr n, Void arg) {
        super.visit(n, arg);
        String value = n.getValue();
        operandsPerMethod.put(value, operandsPerMethod.getOrDefault(value, 0) + 1);
    }

    @Override
    public void visit(IntegerLiteralExpr n, Void arg) {
        super.visit(n, arg);
        String value = n.getValue();
        operandsPerMethod.put(value, operandsPerMethod.getOrDefault(value, 0) + 1);
    }

    @Override
    public void visit(LongLiteralExpr n, Void arg) {
        super.visit(n, arg);
        String value = n.getValue();
        operandsPerMethod.put(value, operandsPerMethod.getOrDefault(value, 0) + 1);
    }

    @Override
    public void visit(StringLiteralExpr n, Void arg) {
        super.visit(n, arg);
        String value = n.getValue();
        operandsPerMethod.put(value, operandsPerMethod.getOrDefault(value, 0) + 1);
    }

    @Override
    public void visit(NullLiteralExpr n, Void arg) {
        super.visit(n, arg);
        String value = "null";
        operandsPerMethod.put(value, operandsPerMethod.getOrDefault(value, 0) + 1);
    }

    @Override
    public void visit(Parameter n, Void arg) {
        super.visit(n, arg);
        String name = n.getNameAsString();
        operandsPerMethod.put(name, operandsPerMethod.getOrDefault(name, 0) + 1);
    }

}
