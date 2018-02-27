package demo.common.jedis;

import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Pool;

public class JedisPoolProvider {
	private JedisPoolConfig config;
	private ResourceBundle bundle;
	private String auth;
	public JedisPoolProvider(String properties) {
		bundle = ResourceBundle.getBundle(properties);  
	    if (bundle == null) {  
	        throw new IllegalArgumentException(  
	                "[redis.properties] is not found!");  
	    }  
	    config = new JedisPoolConfig();  
	    int maxActive = Integer.parseInt(bundle.getString("redis.pool.maxActive"));
	    auth = bundle.getString("redis.pool.auth");
	    config.setMaxTotal(maxActive);
	    config.setMaxIdle(Integer.valueOf(bundle  
	            .getString("redis.pool.maxIdle"))); 
	    //config.setMaxWaitMillis(maxWaitMillis)
	    //config.setMinIdle(minIdle)
	    config.setTestOnBorrow(Boolean.valueOf(bundle  
	            .getString("redis.pool.testOnBorrow")));  
	    config.setTestOnReturn(Boolean.valueOf(bundle  
	            .getString("redis.pool.testOnReturn")));  
	}
	
	public Pool<?> getPool() {
		String[] ips=bundle.getString("redis.pool.ips").split(",");
		if (ips.length>1) {
			List<JedisShardInfo> list= new LinkedList<JedisShardInfo>();
			for (String ip : ips) {
				JedisShardInfo shardInfo = new JedisShardInfo(getHost(ip), getPort(ip));
				shardInfo.setPassword(auth);
				list.add(shardInfo);
			}
			return new ShardedJedisPool(config, list);  
		}
		return new JedisPool(config, getHost(ips[0]),  
				getPort(ips[0]));
	}
	
	public ShardedJedisPool getShardPool(){
		String[] ips=bundle.getString("redis.pool.ips").split(",");
		List<JedisShardInfo> list = null;
		if (ips.length>0) {
			 list= new LinkedList<JedisShardInfo>();
			for (String ip : ips) {
				JedisShardInfo shardInfo = new JedisShardInfo(getHost(ip), getPort(ip));
				shardInfo.setPassword(auth);
				list.add(shardInfo);
			}
		}
		return new ShardedJedisPool(config, list);  
	}
	
	private String getHost(String ip){
		return ip.split(":")[0];
	}
	
	private int getPort(String ip){
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
	public Integer getEffectTime(){
		Integer time = Integer.valueOf(bundle.getString("redis.effect.time"));
		return time;
	}
}
