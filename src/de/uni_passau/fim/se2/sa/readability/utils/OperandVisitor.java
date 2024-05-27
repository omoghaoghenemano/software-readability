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
        super.visit(n, arg);
        String identifier = n.getNameAsString();
        if (!identifier.isEmpty()) {
            operandsPerMethod.put(identifier, operandsPerMethod.getOrDefault(identifier, 0) + 1);
        }
    }

    @Override
    public void visit(Parameter n, Void arg) {
        super.visit(n, arg);
        String identifier = n.getNameAsString();
        operandsPerMethod.put(identifier, operandsPerMethod.getOrDefault(identifier, 0) + 1);
    }

    public Map<String, Integer> getOperandsPerMethod() {
        return operandsPerMethod;
    }
}
