package Compiler2015.AST.Statement.ExpressionStatement;

/**
 * Created by junrushao on 15-4-11.
 */
public class Identifier extends Expression {
	public String name;
	public Identifier(String name) {
		this.name = name;
	}
}
