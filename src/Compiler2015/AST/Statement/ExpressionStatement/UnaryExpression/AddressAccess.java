package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression.ArrayAccess;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Statement.ExpressionStatement.IntConstant;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.Type.*;

/**
 * *e
 */
public class AddressAccess extends UnaryExpression {
	public AddressAccess(Expression e) {
		super(e);
		this.type = Pointer.getPointTo(e.type);
		this.isLValue = !(type instanceof ArrayPointerType) && !(type instanceof FunctionType) && !(type instanceof VoidType);
	}

	public static Expression getExpression(Expression e) {
		if (e.type instanceof VoidType) {
			throw new CompilationError("Type Error");
		}
		if (e.type instanceof IntType) {
			throw new CompilationError("Type Error");
		}
		if (e.type instanceof CharType) {
			throw new CompilationError("Type Error");
		}
		if (e.type instanceof StructOrUnionType) {
			throw new CompilationError("Type Error");
		}
		AddressAccess tmp = new AddressAccess(e);
		return new ArrayAccess(e, new IntConstant(0), tmp.type, tmp.isLValue);
	}

	public String getOperator() {
		return "*";
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		throw new CompilationError("Internal Error.");
	}

	@Override
	public AddressAccess clone() {
		return (AddressAccess) super.clone();
	}

	@Override
	public Expression rebuild() {
		return new AddressAccess(e.rebuild());
	}
}
