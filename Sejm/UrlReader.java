package sejmometr;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONObject;

public class UrlReader {

	public static JSONObject reader(String nUrl) throws Exception {
		
		URL url = new URL(nUrl);
		URLConnection connection = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuilder sb = new StringBuilder();
		while ((inputLine = in.readLine()) != null) {
			sb.append(inputLine);
		}
		in.close();

		String result = String.valueOf(sb);
		JSONObject json = new JSONObject(result);

		return json;
	
	}

}