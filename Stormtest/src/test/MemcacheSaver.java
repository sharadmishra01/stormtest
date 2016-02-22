package test;

import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

public class MemcacheSaver {

	private MemcachedClient client = null;

	private static MemcacheSaver saver = null;

	private MemcacheSaver() throws Exception {
		client = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));

	}

	public synchronized static MemcacheSaver getInstance() throws Exception {
		if (saver == null) {
			saver = new MemcacheSaver();

		}
		return saver;
	}

	public void saveDataToMemCache(String key, Object object) {
		client.set(key, 36000, object);
		System.out.println("Got some thing from client " + client.get(key));

	}

	public Object getDataFromMemCache(String Key) {
		return client.get(Key);

	}

}
