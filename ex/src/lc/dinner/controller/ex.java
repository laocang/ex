package lc.dinner.controller;

public class ex {
	
	public static void main(String[] args){
		double x = 1500;
		for(int i =0;i<7300;i++){
			x = x + (x*0.000106);
			if(i%365==0){
				x = x+1500;
			}
		}
		System.out.println(x);
	}
}
