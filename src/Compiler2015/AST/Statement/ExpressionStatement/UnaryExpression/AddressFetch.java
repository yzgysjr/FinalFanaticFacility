package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Statement.ExpressionStatement.IdentifierExpression;
import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.Type.VariablePointerType;
import Compiler2015.Type.VoidType;

/**
 * &e
 */
public class AddressFetch extends UnaryExpression {
	public AddressFetch(Expression e) {
		super(e);
		this.type = new VariablePointerType(e.type);
		if (e instanceof IdentifierExpression) {
			Environment.globalNonArrayVariablesAndLocalAddressFetchedVariables.add(((IdentifierExpression) e).uId);
		}
	}

	public static Expression getExpression(Expression e) {
		if (!e.isLValue)
			throw new CompilationError("Not LValue.");
		if (e.type instanceof VoidType)
			throw new CompilationError("Type Error");
		return new AddressFetch(e);
	}

	@Override
	public String getOperator() {
		return "&";
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		e.emitCFG(builder);
		if (e instanceof IdentifierExpression) {
			throw new CompilationError("Internal Error.");
		} else {
			if (e.tempRegister instanceof ArrayRegister) {
				e.convertArrayRegisterToPointer(builder);
				tempRegister = e.tempRegister.clone();
			} else if (e.tempRegister instanceof VirtualRegister) {
				tempRegister = e.tempRegister.clone();
			}
		}
	}

	@Override
	public AddressFetch clone() {
		return (AddressFetch) super.clone();
	}

	@Override
	public Expression rebuild() {
		if (e instanceof IdentifierExpression) {
			if (!Environment.globalNonArrayVariablesAndLocalAddressFetchedVariables.contains(((IdentifierExpression) e).uId))
				throw new CompilationError("Internal Error.");
			return e.clone();
		} else
			return new AddressFetch(e.rebuild());
	}
}
