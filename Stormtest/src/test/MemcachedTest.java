package test;
import java.io.IOException;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

/**
 * 
 * @author sharad.mishra
 *
 */

public class MemcachedTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		MemcachedClient c=new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
//		c.set("myobj", 3600, new TestObject("sharad", "mishra"));
		Object myObject=c.get("reservation");
		System.out.println(myObject);
	}

}
