package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Type.Type;

/**
 * a %= b
 */
public class ModuloAssign extends BinaryExpression {
	public ModuloAssign(Expression left, Expression right, Type type, boolean isLValue) {
		super(left, right, type, isLValue);
	}
}
