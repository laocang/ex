package demo.common.jedis;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import demo.common.utils.GsonUtils;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class ShardingRedisClient {
	private static ShardedJedisPool shardedJedisPool;
	private static ResourceBundle resourceBundle;
	private static JedisPoolConfig config;
	private static Integer seconds; //设置key过期时间
	private static String auth;
	static {
		// 读取相关的配置
		resourceBundle = ResourceBundle.getBundle("redis");
		if (resourceBundle == null) {  
		   throw new IllegalArgumentException(  
		                "[redis.properties] is not found!");  
		}  
		int maxActive = Integer.parseInt(resourceBundle.getString("redis.pool.maxActive"));
		int maxIdle = Integer.parseInt(resourceBundle.getString("redis.pool.maxIdle"));
		int maxWait = Integer.parseInt(resourceBundle.getString("redis.pool.maxWait"));
		String ip = resourceBundle.getString("redis.pool.ips");
		auth = resourceBundle.getString("redis.pool.auth");
		//设置配置
		config = new JedisPoolConfig();
		config.setMaxTotal(maxActive);
		config.setMaxIdle(maxIdle);
		config.setMaxWaitMillis(maxWait);
		config.setTestOnBorrow(Boolean.valueOf(resourceBundle  
		            .getString("redis.pool.testOnBorrow")));  
		config.setTestOnReturn(Boolean.valueOf(resourceBundle  
		            .getString("redis.pool.testOnReturn")));  
		
		shardedJedisPool = getShardPool();
		seconds = getEffectTime();
	}
	
	public static ShardedJedisPool getShardPool(){
		List<JedisShardInfo> list = new ArrayList<JedisShardInfo>();
		String[] ips = resourceBundle.getString("redis.pool.ips").split(",");
		if (ips.length>0) {
//			 list= new LinkedList<JedisShardInfo>();
			for (String ip : ips) {
				//设置分片元素信息
				JedisShardInfo shardInfo = new JedisShardInfo(getHost(ip), getPort(ip));
//				System.out.println(getHost(ip)+"这里获取连接的值："+shardInfo.getConnectionTimeout()+","+shardInfo.getSoTimeout()+","+shardInfo.getDb());
				shardInfo.setPassword(auth);//设置redis数据库密码
				list.add(shardInfo);
			}
		}
		return new ShardedJedisPool(config, list);  
	}
	
	private static String getHost(String ip){
		return ip.split(":")[0];
	}
	
	private static int getPort(String ip){
		String port=null;
		if (ip.split(":").length>1) {
			port=ip.split(":")[1];
		}else{
			port="6379";
		}
		return Integer.valueOf(port);
	}
	/**
	 * 这里进行设置有效时间
	 * @return
	 */
	public static Integer getEffectTime(){
		Integer time = Integer.valueOf(resourceBundle.getString("redis.effect.time"));
		return time;
	}

	
	/**
	 * 向缓存中设置字符串内容
	 * @param key key
	 * @param value value
	 * @return
	 * @throws Exception
	 */
	public static boolean  set(String key,String value,int seconds) throws Exception{
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			jedis.set(key, value);
			if(seconds != 0){
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
	 * 根据设置进行设置时间值
	 * @param key
	 * @param value
	 * @param seconds
	 * @return
	 * @throws Exception
	 */
	public static boolean  setex(String key,String value,int seconds) throws Exception{
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			jedis.setex(key, seconds, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			shardedJedisPool.returnResource(jedis);
		}
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
	public static boolean  hset(String key,String field,String value,int seconds) throws Exception{
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
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
	 * 以秒为单位返回 key 的剩余过期时间。
	 * @param key
	 * @param field
	 * @param value
	 * @param seconds
	 * @return
	 * @throws Exception
	 */
	public static Long  httl(String key) throws Exception{
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
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
	public static String  hget(String key,String field) throws Exception{
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
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
	public static Long  hdel(String key,String... fields) throws Exception{
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			Long data = jedis.hdel(key, fields);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			shardedJedisPool.returnResource(jedis);
		}
	}
	
	/**
	 * 向缓存中设置对象
	 * @param key 
	 * @param value
	 * @return
	 */
	public static boolean  set(String key,Object value,int seconds){
		ShardedJedis jedis = null;
		try {
			String objectJson = GsonUtils.objToJson(value);
			jedis = shardedJedisPool.getResource();
			jedis.set(key, objectJson);
			if(seconds != 0){
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
	 * 删除缓存中得对象，根据key
	 * @param key
	 * @return
	 */
	public static boolean del(String key){
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			jedis.del(key);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			shardedJedisPool.returnResource(jedis);
		}
	}
	
	/**
	 * 根据key 获取内容
	 * @param key
	 * @return
	 */
	public static Object get(String key){
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			Object value = jedis.get(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			shardedJedisPool.returnResource(jedis);
		}
	}
	

	/**
	 * 根据key 获取对象
	 * @param key
	 * @return
	 */
	public static <T> T get(String key,Class<T> clazz){
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			String value = jedis.get(key);
			return GsonUtils.jsonToBean(value, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
	public  static <T> T  hget(String key,String field,Class<T> clazz){
		ShardedJedis jedis = null;
		boolean flag = true;
		try {
			jedis = shardedJedisPool.getResource();
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
	 * 将对象转换为json存储
	 * @param key
	 * @param field
	 * @param obj
	 * @param seconds
	 * @return
	 */
	public  boolean  hset(String key,String field,Object obj){
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
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

}
