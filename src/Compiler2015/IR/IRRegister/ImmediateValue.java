package Compiler2015.IR.IRRegister;

import Compiler2015.Exception.CompilationError;

public class ImmediateValue implements IRRegister {
	public int a;

	public ImmediateValue(int a) {
		this.a = a;
	}

	@Override
	public int getUId() {
		return -1;
	}

	@Override
	public String toString() {
		return Integer.toString(a);
	}

	@Override
	public ImmediateValue clone() {
		try {
			return (ImmediateValue) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			throw new CompilationError("Internal Error.");
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ImmediateValue that = (ImmediateValue) o;
		return a == that.a;
	}

	@Override
	public int hashCode() {
		return a;
	}
}
