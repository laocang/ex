package lc.watchshop.model;

public class OrderModel {

	 private String product; 
	 private String name;  
	 private String  tel;    
	 private String privince; 
	 private String  bsm;   
	 private String city;    
	 private String area;    
	 private String addr;    
	 private String price;  
	 private String payway;   
	 private String beizhu;   
	 private String ip;
	 private String lastvisit;
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPrivince() {
		return privince;
	}
	public void setPrivince(String privince) {
		this.privince = privince;
	}
	public String getBsm() {
		return bsm;
	}
	public void setBsm(String bsm) {
		this.bsm = bsm;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPayway() {
		return payway;
	}
	public void setPayway(String payway) {
		this.payway = payway;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getLastvisit() {
		return lastvisit;
	}
	public void setLastvisit(String lastvisit) {
		this.lastvisit = lastvisit;
	}
	@Override
	public String toString() {
		return "OrderModel [addr=" + addr + ", area=" + area + ", beizhu="
				+ beizhu + ", bsm=" + bsm + ", city=" + city + ", ip=" + ip
				+ ", lastvisit=" + lastvisit + ", name=" + name + ", payway="
				+ payway + ", price=" + price + ", privince=" + privince
				+ ", product=" + product + ", tel=" + tel + "]";
	}
}
