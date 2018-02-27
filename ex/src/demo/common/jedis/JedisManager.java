package demo.common.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class JedisManager {
	public static JedisProvider getRedisByHost(String host) {
		return new JedisProvider(host); 
	}
	
	public static JedisProvider getRedis(String host,int port) {
		return new JedisProvider(host, port); 
	}
	/**
	 * 从连接池中获取的reids，用完记得close
	 * @param properties
	 * @return
	 */
	public static JedisProvider getRedis(String properties) {
		return new JedisProvider((Jedis) getRedisByPool(properties)); 
	}
	/**
	 * 从连接池中获取的reids，用完记得close
	 * @param properties
	 * @return
	 */
	public static ShardedJedisProvider getShardedJedis(String properties) {
		return new ShardedJedisProvider(getShardedJedisPool(properties),getEffectDate(properties)); 
//		return new ShardedJedisProvider(ShardingRedisClient.getShardPool(),ShardingRedisClient.getEffectTime()); 
	}
	
	public static ShardedJedisPool getShardedJedisPool(String properties){
		JedisPoolProvider provider=new JedisPoolProvider(properties);
		ShardedJedisPool shardedJedisPool = (ShardedJedisPool) provider.getShardPool();
		return shardedJedisPool;
	}
	
	private static Object getRedisByPool(String properties) {
		JedisPoolProvider provider=new JedisPoolProvider(properties);
		return provider.getPool().getResource();
	}
	
	/**
	 * 获取有效时间
	 * @param properties
	 * @return
	 */
	public static Integer getEffectDate(String properties){
		JedisPoolProvider provider=new JedisPoolProvider(properties);
		return provider.getEffectTime();
	}
	/**
	 * 释放资源
	 * @param properties
	 */
	public static void poolreturnResource(String properties){
		JedisPoolProvider provider=new JedisPoolProvider(properties);
		ShardedJedisPool shardedJedisPool = (ShardedJedisPool) provider.getPool();
		shardedJedisPool.returnResource((ShardedJedis) getRedisByPool(properties));
	}
}
