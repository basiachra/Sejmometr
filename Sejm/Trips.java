package sejmometr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;


public class Trips {

	private Envoy envoy;
	private HashMap<String, String> hmap;
	private int theMostTravels;
	private String envoyName;
	private ArrayList<String> italy;

	public Trips() throws Exception {
		envoy = new Envoy();
		this.hmap = envoy.getMap();
	}

	public void theTrips(int wybor) throws Exception {

		theMostTravels = 0; // najwiêcej podró¿y
		envoyName = ""; 

		int dni = 0; // najd³u¿ej w podró¿y
		int maxdni = 0;

		double koszt = 0.0; // najdro¿sza podró¿
		double maxkoszt = 0.0;

		String wlochy = "W³ochy"; // podró¿ do w³och
		boolean exist = false;
		String kraj = "";
		this.italy = new ArrayList<String>();

		Iterator<Entry<String, String>> it = hmap.entrySet().iterator();
		while (it.hasNext()) {

			Map.Entry<String, String> pair = it.next();
			String id = (String) pair.getKey();

			JSONObject json = UrlReader.reader("https://api-v3.mojepanstwo.pl/dane/poslowie/" + id + ".json?layers[]=wyjazdy");
			JSONObject data = json.getJSONObject("data");
			String name = data.getString("ludzie.nazwa");
			int trips = data.getInt("poslowie.liczba_wyjazdow");

			if (wybor == 4) {
				if (trips > theMostTravels) {
					theMostTravels = trips;
					envoyName = name;
				}
			} else {

				if (trips != 0) {
					
					JSONObject layers = json.getJSONObject("layers");
					Object checkIf = layers.get("wyjazdy");

					if (checkIf instanceof JSONArray) {
						
						JSONArray wyjazdy = layers.getJSONArray("wyjazdy");
					
						for (int i = 0; i < wyjazdy.length(); i++) {

							JSONObject warstwaPole = wyjazdy.getJSONObject(i);

							if (wybor == 5) {
								dni += warstwaPole.getInt("liczba_dni");
							}

							if (wybor == 6) {
								koszt = warstwaPole.getDouble("koszt_suma");
							}

							if (wybor == 7) {
								kraj = warstwaPole.getString("kraj");
							}

						}

						if (wybor == 5) {
							if (dni > maxdni) {
								maxdni = dni;
								envoyName = name;
							}
						}

						if (wybor == 6) {
							if (koszt > maxkoszt) {
								maxkoszt = koszt;
								envoyName = name;
							}
						}

						if (wybor == 7) {
							if (wlochy.equals(kraj))
								exist = true;
						}

					}
					else {
						
						JSONArray pusteWyjazdy = new JSONArray();						
					}
				}

			}

			if (exist)
				this.italy.add(envoyName);

		}
		if (wybor == 4)
			System.out.println("Najwiêcej wyjazdów zagranicznych:  " + envoyName + ". Liczba wyjazdów zagranicznych: " + theMostTravels);
		if (wybor == 5)
			System.out.println("Najd³u¿ej za granic¹: " + maxdni + " dni przbywa³ pose³: " + envoyName);
		if (wybor == 6)
			System.out.println("Najdro¿sza podró¿ wynios³a: " + maxkoszt + " i odby³ j¹ pose³: " + envoyName);
		if (wybor == 7) {
			for (int j = 0; j < italy.size(); j++) {
				System.out.println(italy.get(j));
			}

		}
	}

}
