package br.com.rrc.model;

public class Util {
	
	public static boolean IsNullOrEmpty(String s){
		
		if(s == null){
			return true;
		}
		
		if(s.replace(" ", "").isEmpty()){
			return true;
		}
		return false;
	}
	
}
