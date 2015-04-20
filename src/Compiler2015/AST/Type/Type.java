package Compiler2015.AST.Type;

import Compiler2015.AST.ASTNode;
import Compiler2015.AST.SizeMeasurable;

public abstract class Type extends ASTNode implements SizeMeasurable {
	/**
	 * @param a one type
	 * @param b the other type
	 * @return if a could be a parameter while b is the counterpart in parameter list of function declaration
	 * <p/>
	 * 1. void types are not allowed
	 * 2. Struct / Union types are compared strictly
	 * 3. the resting types, including numeric ones and pointers, are allowed to cast as they wish to
	 */
	public static boolean suitable(Type a, Type b) {
		// Void
		if (a instanceof VoidType || b instanceof VoidType)
			return false;

		// Struct / Union
		if (a instanceof StructOrUnionType && b instanceof StructOrUnionType)
			return ((StructOrUnionType) a).ref.uId == ((StructOrUnionType) b).ref.uId;
		return !(a instanceof StructOrUnionType || b instanceof StructOrUnionType);

	}

	public static boolean isNumeric(Type x) {
		return (x instanceof IntType) || (x instanceof CharType);
	}
}
