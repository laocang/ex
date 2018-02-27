package demo.common.jedis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import redis.clients.jedis.Jedis;

public class JedisProvider implements IJedisCommand{
	private Jedis jedis;

	public JedisProvider(Jedis jedis) {
		this.jedis = jedis;
	}

	public JedisProvider(String host, int port) {
		this.jedis = new Jedis(host, port);
	}

	public JedisProvider(String host) {
		this.jedis = new Jedis(host);
	}

	public <T> String set(String key, T value) {
		return set(key, objectToByte(value));
	}

	public <T> String set(String key, T value, int seconds) {
		return set(key, objectToByte(value), seconds);
	}

	public <T> void set(String key, T value, long unixTime) {
		set(key, value);
		expireAt(key, unixTime);
	}

	public String set(String key, byte[] value) {
		return jedis.set(key.getBytes(), value);
	}

	public String set(String key, byte[] value, int seconds) {
		if (jedis.get(key) != null) {
			return jedis.set(key.getBytes(), value, "XX".getBytes(),
					"EX".getBytes(), seconds);
		}
		return jedis.set(key.getBytes(), value, "NX".getBytes(),
				"EX".getBytes(), seconds);
	}

	public void set(String key, byte[] value, long unixTime) {
		set(key, value);
		expireAt(key, unixTime);
	}

	public String set(String key, String value) {
		return jedis.set(key, value);
	}

	public String set(String key, String value, int seconds) {
		if (jedis.get(key) != null) {
			return jedis.set(key, value, "XX", "EX", seconds);
		}
		return jedis.set(key, value, "NX", "EX", seconds);
	}

	public void set(String key, String value, long unixTime) {
		set(key, value);
		expireAt(key, unixTime);
	}

	@SuppressWarnings("unchecked")
	public <T> T getObj(String key) {
		return (T) byteToObject(jedis.get(key.getBytes()));
	}

	public byte[] getBytes(String key) {
		return jedis.get(key.getBytes());
	}

	public String get(String key) {
		return jedis.get(key);
	}

	public long del(String key) {
		return jedis.del(key);
	}

	public String auth(String password) {
		return jedis.auth(password);
	}

	public boolean exists(String key) {
		return jedis.exists(key);
	}

	public long expire(String key, int seconds) {
		return jedis.expire(key, seconds);
	}

	public long expireAt(String key, long unixTime) {
		return jedis.expireAt(key, unixTime);
	}

	public void close() {
		jedis.close();//
	}

	public String select(int index) {
		return jedis.select(index);
	}

	private Object byteToObject(byte[] bytes) {
		Object obj = null;
		try {
			ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
			ObjectInputStream oi = new ObjectInputStream(bi);
			obj = oi.readObject();
			bi.close();
			oi.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	private byte[] objectToByte(Object obj) {
		byte[] bytes = null;
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(obj);
			bytes = bo.toByteArray();
			bo.close();
			oo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}

	public void flushDB() {
		jedis.flushDB(); //清空数据信息
	}

	public void expireByKey(String key,int seconds) {
		jedis.expire(key, seconds);
		
	}

	public boolean hset(String key, String field, String value) {
		// TODO Auto-generated method stub
		return false;
	}

	public Long httl(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	public String hget(String key, String field) {
		// TODO Auto-generated method stub
		return null;
	}

	public Long hdel(String key, String... fields) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hset(String key, String field, Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public <T> T hget(String key, String field, Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	public long append(String key, String value) {
		return jedis.append(key, value);
	}

}
