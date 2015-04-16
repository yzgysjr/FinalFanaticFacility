package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Type.Type;

/**
 * a >> b
 */
public class ShiftRight extends BinaryExpression {
	public ShiftRight(Expression left, Expression right, Type type, boolean isLValue) {
		super(left, right, type, isLValue);
	}
}
