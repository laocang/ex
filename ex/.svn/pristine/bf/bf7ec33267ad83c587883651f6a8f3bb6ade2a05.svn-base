package demo.common.jedis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import demo.common.utils.GsonUtils;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.ShardedJedisPool;

public class ShardedJedisProvider implements IJedisCommand{
	private ShardedJedis jedis;
	private ShardedJedisPool shardedJedisPool;
	private Integer seconds; //设置key过期时间

	public ShardedJedisProvider(ShardedJedisPool shardedJedisPool,Integer seconds) {
		this.shardedJedisPool = shardedJedisPool;
		this.seconds = seconds;
	}

	public <T> String set(String key, T value) {
		try {
			this.jedis = shardedJedisPool.getResource();
			return set(key, objectToByte(value),seconds);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			shardedJedisPool.returnResource(jedis);
		}
		return null;
	}

	public <T> String set(String key, T value, int seconds) {
		try {
			seconds = this.seconds;
			return set(key, objectToByte(value), seconds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public <T> void set(String key, T value, long unixTime) {
		try {
			set(key, value);
			expireAt(key, unixTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public String set(String key, byte[] value) {
		try {
			this.jedis = shardedJedisPool.getResource();
			return jedis.set(key.getBytes(), value);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			shardedJedisPool.returnResource(jedis);
		}
		return null;
	}

	public String set(String key, byte[] value, int seconds) {
		try {
			this.jedis = shardedJedisPool.getResource();
			if (jedis.get(key) != null) {
				return jedis.set(key.getBytes(), value, "XX".getBytes(),
						"EX".getBytes(), seconds);
			}
			return jedis.set(key.getBytes(), value, "NX".getBytes(),
					"EX".getBytes(), seconds);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			shardedJedisPool.returnResource(jedis);
		}
		return null;
	}

	public void set(String key, byte[] value, long unixTime) {
		try {
			set(key, value);
			expireAt(key, unixTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	public String set(String key, String value) {
		try {
			this.jedis = shardedJedisPool.getResource();
			return jedis.set(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			shardedJedisPool.returnResource(jedis);
		}
		return null;
	}

	public String set(String key, String value, int seconds) {
		try {
			this.jedis = shardedJedisPool.getResource();
			if (jedis.get(key) != null) {
				return jedis.set(key, value, "XX", "EX", seconds);
			}
			return jedis.set(key, value, "NX", "EX", seconds);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			shardedJedisPool.returnResource(jedis);
		}
		return null;
		
	}

	public void set(String key, String value, long unixTime) {
		try {
			set(key, value);
			expireAt(key, unixTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unchecked")
	public <T> T getObj(String key) {
		try {
			this.jedis = shardedJedisPool.getResource();
			if(jedis.get(key.getBytes()) == null || "".equals(jedis.get(key.getBytes()))){
				return null;
			}
			return (T) byteToObject(jedis.get(key.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			shardedJedisPool.returnResource(jedis);
		}
		return null;
	}

	public byte[] getBytes(String key) {
		try {
			this.jedis = shardedJedisPool.getResource();
			return jedis.get(key.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			shardedJedisPool.returnResource(jedis);
		}
		return null;
		
	}

	public String get(String key) {
		try {
			this.jedis = shardedJedisPool.getResource();
			return jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			shardedJedisPool.returnResource(jedis);
		}
		return null;
		
	}

	public long del(String key) {
		try {
			this.jedis = shardedJedisPool.getResource();
			return jedis.del(key);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			shardedJedisPool.returnResource(jedis);
		}
		return 0l;
		
	}

	public boolean exists(String key) {
		try {
			this.jedis = shardedJedisPool.getResource();
			return jedis.exists(key);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			shardedJedisPool.returnResource(jedis);
		}
		return false;
		
	}

	public long expire(String key, int seconds) {
		try {
			this.jedis = shardedJedisPool.getResource();
			return jedis.expire(key, seconds);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			shardedJedisPool.returnResource(jedis);
		}
		return 0l;
		
	}

	public long expireAt(String key, long unixTime) {
		try {
			this.jedis = shardedJedisPool.getResource();
			return jedis.expireAt(key, unixTime);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			shardedJedisPool.returnResource(jedis);
		}
		return 0l;
		
	}

	public void close() {
		try {
			this.jedis = shardedJedisPool.getResource();
			jedis.close();// 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			shardedJedisPool.returnResource(jedis);
		}
		//应用关闭时释放 连接池
		shardedJedisPool.destroy();
		
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
		
	}

	public void expireByKey(String key, int seconds) {
		// TODO Auto-generated method stub
	}

	/**
	 * 哈希设置值
	 * @param key
	 * @param field
	 * @param value
	 * @param seconds
	 * @return
	 * @throws Exception
	 */
	public  boolean  hset(String key,String field,String value){
		try {
			this.jedis = shardedJedisPool.getResource();
			jedis.hset(key, field, value);
			if(seconds != 0){
				//设置成功返回 1 。 当 key 不存在或者不能为 key 设置过期时间时(比如在低于 2.1.3 版本的 Redis 中你尝试更新 key 的过期时间)返回 0 
				jedis.expire(key, seconds);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			shardedJedisPool.returnResource(jedis);
		}
	}
	/**
	 * 将对象转换为json存储
	 * @param key
	 * @param field
	 * @param obj
	 * @param seconds
	 * @return
	 */
	public  boolean  hset(String key,String field,Object obj){
		try {
			this.jedis = shardedJedisPool.getResource();
			String value = GsonUtils.objToJson(obj);
			jedis.hset(key, field, value);
			if(seconds != 0){
				//设置成功返回 1 。 当 key 不存在或者不能为 key 设置过期时间时(比如在低于 2.1.3 版本的 Redis 中你尝试更新 key 的过期时间)返回 0 
				jedis.expire(key, seconds);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			shardedJedisPool.returnResource(jedis);
		}
	}
	/**
	 * 将json转换为对象进行获取
	 * @param key
	 * @param field
	 * @param clazz
	 * @return
	 */
	public <T> T  hget(String key,String field,Class<T> clazz){
		boolean flag = true;
		try {
			this.jedis = shardedJedisPool.getResource();
			String data = jedis.hget(key, field);
			T obj = (T) GsonUtils.jsonToBean(data, clazz);
			return obj;
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
			return null;
		}finally{
			if(flag){
				shardedJedisPool.returnResource(jedis);
			}else{
				shardedJedisPool.returnBrokenResource(jedis);
			}
			
		}
	}
	
	/**
	 * 以秒为单位返回 key 的剩余过期时间。
	 * @param key
	 * @param field
	 * @param value
	 * @param seconds
	 * @return
	 * @throws Exception
	 */
	public  Long  httl(String key){
		try {
			this.jedis = shardedJedisPool.getResource();
			Long count = jedis.ttl(key);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0l;
		}finally{
			shardedJedisPool.returnResource(jedis);
		}
	}
	/**
	 * 哈希获取值
	 * @param key
	 * @param field
	 * @return
	 * @throws Exception
	 */
	public String  hget(String key,String field){
		try {
			this.jedis = shardedJedisPool.getResource();
			String data = jedis.hget(key, field);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			shardedJedisPool.returnResource(jedis);
		}
	}
	/**
	 * 哈希删除值
	 * @param key
	 * @param fields
	 * @return
	 * @throws Exception
	 */
	public  Long  hdel(String key,String... fields){
		try {
			this.jedis = shardedJedisPool.getResource();
			Long data = jedis.hdel(key, fields);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			shardedJedisPool.returnResource(jedis);
		}
	}

	public long append(String key, String value) {
		// TODO Auto-generated method stub
		return 0;
	}
}
