package br.com.rrc.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.net.ParseException;

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

	public static String getDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd/MM/yyyy HH:mm:ss", Locale.getDefault());
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static String getDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd/MM/yyyy", Locale.getDefault());
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String getDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd/MM/yyyy", Locale.getDefault());
		return dateFormat.format(date);
	}
	
	
	public static Date getDate(String s){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy",Locale.getDefault());
		
		try {
			return sdf.parse(s);
		} catch (java.text.ParseException e) {
		}
		return null;
	}
}
