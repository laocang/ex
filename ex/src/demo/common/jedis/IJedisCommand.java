package demo.common.jedis;

public interface IJedisCommand {
	long append(String key,String value);
	
	<T> String set(String key, T value);

	<T> String set(String key, T value, int seconds);

	<T> void set(String key, T value, long unixTime);

	String set(String key, byte[] value);

	String set(String key, byte[] value, int seconds);

	void set(String key, byte[] value, long unixTime);

	String set(String key, String value);

	String set(String key, String value, int seconds);

	void set(String key, String value, long unixTime);
	
	<T> T getObj(String key);

	String get(String key);
	
	byte[] getBytes(String key);
	
	long del(String key);

	boolean exists(String key);

	long expire(String key, int seconds);

	long expireAt(String key, long unixTime);

	void close();
	
	public void flushDB();
	/**
	 * 设置过期时间
	 * @param key
	 */
	public void expireByKey(String key,int seconds);
	
	public  boolean  hset(String key,String field,String value);
	
	public  Long  httl(String key);
	
	public String  hget(String key,String field);
	
	public  Long  hdel(String key,String... fields);
	
	public  boolean  hset(String key,String field,Object obj);
	
	public <T> T  hget(String key,String field,Class<T> clazz);
}
