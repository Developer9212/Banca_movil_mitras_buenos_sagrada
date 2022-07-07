package com.fenoreste.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class main {
	public static void main(String args[]) {
		intervale();
		/*EliminarArchivosThread h1 = new EliminarArchivosThread();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			String f = "2022-02-03 03:00:00 AM";
			Date d = new Date();
			d = sdf.parse(f);
			System.out.println("ohur::"+d.getHours());
			d.setHours(d.getHours()+12);
			SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // sistema de 12 horas
			System.out.println(ss.format(d));

			Date date = new Date();
			date = sdf.parse(f);
			SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd h:m:ss aa"); // sistema de 24 horas
			String LgTime = sdformat.format(date);
			Date da= sdf.parse(LgTime);
			System.out.println("Da:"+da);
			System.out.println(LgTime);
			
			
			SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss", Locale.ENGLISH);
	        Date firstDate = sdf2.parse("04/22/2020 05:05:05");
	        Date secondDate = sdf2.parse("04/22/2020 05:06:06");

	        long diff = secondDate.getTime() - firstDate.getTime();

	        TimeUnit time = TimeUnit.DAYS; 
	        TimeUnit timeHora = TimeUnit.HOURS; 
	        TimeUnit timeMinutos = TimeUnit.MINUTES;
	        TimeUnit timeSegundos = TimeUnit.SECONDS;
	        
	        long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
	        long diffrenceHour = timeHora.convert(diff, TimeUnit.MILLISECONDS);
	        long diffrenceMinutos = timeMinutos.convert(diff, TimeUnit.MILLISECONDS);
	        long diffrenceSegundos = timeSegundos.convert(diff, TimeUnit.MILLISECONDS);
	        System.out.println("The difference in days is : "+diffrence+" total de horas:"+diffrenceHour+" total de minutos "+diffrenceMinutos+" total de segundos "+diffrenceSegundos);
	        
		} catch (Exception e) {
			System.out.println("El erro es :" + e.getMessage());
		}*/
	} 
 
	public static void intervale() {
	SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");
	Date date = null;
	try {
		date = d.parse("31-08-2021");
	} catch (ParseException ex) {
		System.out.println("Error al parsear fecha:" + ex.getMessage());
	}
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	cal.add(Calendar.DAY_OF_YEAR, 62);
	System.out.println("La fecha calculada es:"+cal.getTime());
}
}
