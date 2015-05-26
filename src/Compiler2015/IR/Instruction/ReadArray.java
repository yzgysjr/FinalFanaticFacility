package Compiler2015.IR.Instruction;

import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.Arithmetic.Arithmetic;

/**
 * rd <- rs, i.e. c = a[b]
 */
public class ReadArray extends Arithmetic implements TripleSource {
	public ArrayRegister rs;
	public int memoryVersion = 0;

	public ReadArray(VirtualRegister rd, ArrayRegister rs) {
		this.rd = rd.clone();
		this.rs = rs.clone();
	}

	@Override
	public int[] getAllDef() {
		return new int[]{rd.getUId()};
	}

	@Override
	public int[] getAllUse() {
		return new int[]{rs.a.getUId(), rs.b.getUId(), 0};
	}

	@Override
	public void setAllDefVersion(int[] version) {
		rd.setVersion(version[0]);
	}

	@Override
	public void setAllUseVersion(int[] version) {
		rs.a.setVersion(version[0]);
		memoryVersion = version[2];
	}

	@Override
	public String toString() {
		return "ReadArray " + rd + " = " + rs + " -> M = " + memoryVersion;
	}

	@Override
	public int getA() {
		return rs.a.getUId();
	}

	@Override
	public int getB() {
		return rs.b.getUId();
	}

	@Override
	public int getC() {
		return 0;
	}

	@Override
	public void setAVersion(int x) {
		rs.a.setVersion(x);
	}

	@Override
	public void setBVersion(int x) {
	}

	@Override
	public void setCVersion(int x) {
		memoryVersion = x;
	}
}
