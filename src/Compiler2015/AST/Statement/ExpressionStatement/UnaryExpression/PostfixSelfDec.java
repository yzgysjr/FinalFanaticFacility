package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.Arithmetic.SubtractReg;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.Move;
import Compiler2015.Type.ArrayPointerType;
import Compiler2015.Type.StructOrUnionType;
import Compiler2015.Type.VoidType;

/**
 * e--
 */
public class PostfixSelfDec extends UnaryExpression {
	public PostfixSelfDec(Expression e) {
		super(e);
		this.type = e.type;
	}

	@Override
	public String getOperator() {
		return "Postfix --";
	}

	public static Expression getExpression(Expression a1) {
		if (!a1.isLValue)
			throw new CompilationError("Not LValue.");
		if (a1.type instanceof VoidType || a1.type instanceof StructOrUnionType || a1.type instanceof ArrayPointerType)
			throw new CompilationError("Such type supports no self-decrement.");
/*
		if (a1 instanceof IntConstant)
			return new IntConstant(((IntConstant) a1).c - 1);
		if (a1 instanceof CharConstant)
			return new CharConstant((char) (((CharConstant) a1).c - 1));
*/
		return new PostfixSelfDec(a1);
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		e.emitCFG(builder);
		tempRegister = Environment.getTemporaryRegister();
		builder.addInstruction(new Move(tempRegister, e.tempRegister));
		int tp = Environment.getTemporaryRegister();
		builder.addInstruction(new SubtractReg(tp, tempRegister, Environment.getImmRegister(builder, 1)));
		builder.addInstruction(new Move(e.tempRegister, tp));
	}
}
