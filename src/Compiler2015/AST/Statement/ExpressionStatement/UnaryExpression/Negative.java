package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Statement.ExpressionStatement.IntConstant;
import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.Arithmetic.NegateReg;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.Type.IntType;
import Compiler2015.Type.Type;

/**
 * -e
 */
public class Negative extends UnaryExpression {
	public Negative(Expression e) {
		super(e);
		this.type = new IntType();
	}

	@Override
	public String getOperator() {
		return "-";
	}

	public static Expression getExpression(Expression e) {
		if (!Type.isNumeric(e.type))
			throw new CompilationError("Cannot put - before this type");
		Integer v = Expression.toInt(e);
		if (v != null)
			return new IntConstant(-v);
		return new Negative(e);
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		e.emitCFG(builder);
		tempRegister = Environment.getTemporaryRegister();
		builder.addInstruction(new NegateReg(tempRegister, e.tempRegister));
	}

}
