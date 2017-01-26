package sejmometr;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;

public class Expenses {

	private Envoy envoy;
	private HashMap<String, String> hmap;

	public Expenses() throws Exception {
		envoy = new Envoy();
		this.hmap = envoy.getMap();
	}

	public void envoyExpenses(String id, int wybor) throws Exception {

		// pobieranie warstw
		JSONObject json = UrlReader.reader("https://api-v3.mojepanstwo.pl/dane/poslowie/" + id + ".json?layers[]=krs&layers[]=wydatki");
		JSONObject layers = json.getJSONObject("layers");
		JSONObject wydatki = layers.getJSONObject("wydatki");
		JSONArray roczniki = wydatki.getJSONArray("roczniki");

		double sumaCalkowita = 0.0;
		double suma = 0.0;
		double suma2 = 0.0;
		double result = 0.0;

		for (int i = 0; i < roczniki.length(); i++) {

			JSONObject warstwaRoczniki = roczniki.getJSONObject(i);
			JSONArray warstwaPola = warstwaRoczniki.getJSONArray("pola");

			if (wybor == 1) { // wszystkie wydatki dla jednego pos³a
				for (int j = 0; j < warstwaPola.length(); j++) {
					result = warstwaPola.getDouble(j);
					suma += result;
				}
				sumaCalkowita += suma;
			}

			if(wybor == 2) { // tylko za drobne naprawy wybor ==2
				result = warstwaPola.getDouble(12);
				suma2 += result;

			}
		}

		if (wybor == 1) {
			System.out.println("Suma wydatków wybranego pos³a  wynosi: " + sumaCalkowita);
		}

		else
			System.out.println(
					"Suma wydatków na 'drobne naprawy i remonty biura poselskiego' wybranego pos³a wynosi: " + suma2);

	}

	public void envoysExpensesAvg() throws Exception {

		int numberOfEnvoys = 0;
		double suma = 0.0;
		double avg = 0.0;

		Iterator<Entry<String, String>> it = hmap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> pair = it.next();
			String id = (String) pair.getKey();

			// pobieranie warstw
			JSONObject json = UrlReader.reader("https://api-v3.mojepanstwo.pl/dane/poslowie/" + id + ".json?layers[]=krs&layers[]=wydatki");
			JSONObject layers = json.getJSONObject("layers");
			JSONObject wydatki = layers.getJSONObject("wydatki");
			JSONArray roczniki = wydatki.getJSONArray("roczniki");

			double sumaCalkowita = 0.0;
				
			numberOfEnvoys++;
				
			for (int i = 0; i < roczniki.length(); i++) {
	

				JSONObject warstwaRoczniki = roczniki.getJSONObject(i);
				JSONArray warstwaPola = warstwaRoczniki.getJSONArray("pola");

				double sumaEnvoy = 0.0;
				for (int j = 0; j < warstwaPola.length(); j++) {
					double result = warstwaPola.getDouble(j);
					sumaEnvoy += result;
				}

				sumaCalkowita += sumaEnvoy;
			}

			suma += sumaCalkowita;
		}

		avg = suma / numberOfEnvoys;
		System.out.println("Pos³owie œrednio wydali: " + avg);

	}

}
