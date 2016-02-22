package test;
import java.io.Serializable;


public class TestObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4689329376129056628L;
	String name;
	public TestObject(String name, String val) {
		super();
		this.name = name;
		this.val = val;
	}
	String val;
	@Override
	public String toString() {
		return "TestObject [name=" + name + ", val=" + val + "]";
	}

}
