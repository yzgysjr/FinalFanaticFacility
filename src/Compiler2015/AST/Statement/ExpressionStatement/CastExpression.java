package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.Move;
import Compiler2015.Type.StructOrUnionType;
import Compiler2015.Type.Type;
import Compiler2015.Type.VoidType;

/**
 * (castTo)e
 */
public class CastExpression extends Expression {
	public Type castTo;
	public Expression e;

	public CastExpression(Type castTo, Expression e) {
		this.type = castTo;
		this.castTo = castTo;
		this.e = e;
	}

	public static Expression getExpression(Type t, Expression c) {
		if (t instanceof VoidType)
			throw new CompilationError("Cannot cast to void");
		if (t.equals(c.type))
			return c;
		if (t instanceof StructOrUnionType || c.type instanceof StructOrUnionType)
			throw new CompilationError("Cast failed");
		if (c instanceof CharConstant) {
			char v = ((CharConstant) c).c;
			return new IntConstant((int) v);
		}
		return new CastExpression(t, c);
	}

	public static boolean castable(Type from, Type to) {
		return from.equals(to) || !(from instanceof StructOrUnionType || to instanceof StructOrUnionType);
	}

	@Override
	public String toString() {
		return String.format("(CastTo %s %s)", castTo, e);
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		e.emitCFG(builder);
		e.readInArrayRegister(builder);
		if (e.type.sizeof() == castTo.sizeof())
			tempRegister = e.tempRegister.clone();
		else {
			tempRegister = Environment.getVirtualRegister();
			builder.addInstruction(new Move((VirtualRegister) tempRegister, e.tempRegister));
		}
	}

	@Override
	public CastExpression clone() {
		CastExpression ret = (CastExpression) super.clone();
		ret.castTo = ret.castTo.clone();
		ret.e = ret.e.clone();
		return ret;
	}

	@Override
	public Expression rebuild() {
		return new CastExpression(castTo, e.rebuild());
	}
}
