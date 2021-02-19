package edu.escuelaing.arep.TrigCalculator;
import static spark.Spark.get;
import static spark.Spark.port;
import java.lang.Math;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class TrigCalculator {
	
	public static void main(String[] args) {
		port(getPort());
		get("/trigcalculator", (req, res) ->{
			String number = req.queryParams("val");
			String triFuction = req.queryParams("trifunction");
			Double numberDouble = Double.parseDouble(number);
			Double respuesta = null;
			if(triFuction.equals("cos")) {
				respuesta = Math.cos(numberDouble);
			}else if(triFuction.equals("sin")) {
				respuesta = Math.sin(numberDouble);
			}else if(triFuction.equals("tan")) {
				respuesta = Math.tan(numberDouble);
			}
			String resFinal = Double.toString(respuesta);
			String json = "{ \"resultado\": "+resFinal+"}";
			JsonObject convertedObject = new Gson().fromJson(json, JsonObject.class);

			return convertedObject;
		});
	}
	
	/**
	 * @return retorna el el puerto desde el entorno del sistema operativo
	 */
	static int getPort() {
		if (System.getenv("PORT") != null) {
			return Integer.parseInt(System.getenv("PORT"));
		}
		return 4567; // returns default port if heroku-port isn't set (i.e. on localhost)
	}

}

