package sejmometr;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.json.JSONArray;
import org.json.JSONObject;



public class Envoy {

	private static HashMap<String, String> hmap;

	public Envoy() throws Exception {
		hmap = new HashMap<String, String>();
		Envoy.hmap = Envoy.createMap();
	}

	public HashMap<String, String> getMap() {
		return hmap;
	}
	
	static HashMap<String, String> createMap() throws Exception {
		JSONObject json = UrlReader.reader("https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=8");

		JSONObject links = json.getJSONObject("Links"); // strony 
		String last = links.getString("last"); // ostatnia strona

		String next = "";

		while (!next.equals(last)) {
			
			JSONArray dataobject = json.getJSONArray("Dataobject");

			for (int i = 0; i < dataobject.length(); i++) {

				JSONObject warstwaDlaId = dataobject.getJSONObject(i); // w warstwie dataobject wybieramy id
				String id = warstwaDlaId.getString("id");

				JSONObject warstwaDlaName = warstwaDlaId.getJSONObject("data"); // wchodzimy do data < dla konkretnego id> i pobieramy imiê
				String name = warstwaDlaName.getString("ludzie.nazwa");

				hmap.put(id, name);

			}

			next = links.getString("next");	//prze³¹czanie na kolejn¹ stronê
			json = UrlReader.reader(next);
			links = json.getJSONObject("Links");

		}

		return hmap;
	}
	
	public static String getEnvoyId(HashMap<String, String> hmap, String envoyName) {

		for (Entry<String, String> entry : hmap.entrySet()) {
			if (Objects.equals(envoyName, entry.getValue())) {
				return entry.getKey(); // zwraca id jako string
			}
		}
		return null;
	}

	public static boolean ifExist(String envoyName) throws Exception {
		boolean existEnvoy = false;

		Envoy envoy = new Envoy();
		HashMap<String, String> hmap = envoy.getMap();
		Iterator<Entry<String, String>> it = hmap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> pair = it.next();
			String name = (String) pair.getValue();
			if (name.equals(envoyName))
				existEnvoy = true;
		}

		return existEnvoy;
	}

}
