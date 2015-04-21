package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.Utility.Utility;

/**
 * a, b, c
 */
public class CommaExpression extends Expression {
	public Expression e1, e2;

	public CommaExpression(Expression e1, Expression e2) {
		this.e1 = e1;
		this.e2 = e2;
		this.type = e2.type;
		this.isLValue = e2.isLValue;
	}

	public static Expression getExpression(Expression e1, Expression e2) {
		Integer v1 = toInt(e1), v2 = toInt(e2);
		if (v1 != null && v2 != null)
			return new IntConstant(v2);
		else
			return new CommaExpression(e1, e2);
	}

	@Override
	public String toString(int depth) {
		return Utility.getIndent(depth).append(e1.toString(depth + 1)).append(e2.toString(depth + 1)).toString();
	}
}
