package thrifty.api.net;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WikiaConnection implements UrlConnection {
	
	private final static String WIKIA_URL = "http://leagueoflegends.wikia.com/api.php?";

	public WikiaConnection() {
		
	}

	public String get(String item) {
		HttpURLConnection connection = null;
		
		try {
			URL targetURL = new URL(constructURL(item));
			connection = getOpenURLConnection(targetURL);
			String result = getStringFromOpenConnection(connection);
			return result;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(connection != null)
				connection.disconnect();
		}
		
		return null;
	}
	
	private String constructURL(String item) {
		// format=json&action=query&titles=Infinity%20Edge&prop=revisions&rvprop=content
		StringBuilder sb = new StringBuilder();
		sb.append(WIKIA_URL);
		sb.append("format=json&action=query&titles=");
		sb.append(item);
		sb.append("&prop=revisions&rvprop=content");
		return sb.toString();
	}

	private HttpURLConnection getOpenURLConnection(URL url) throws IOException {
		HttpURLConnection result = (HttpURLConnection) url.openConnection();
		result.setRequestMethod("GET");
		result.setRequestProperty("Content-Type", "application/json");
		result.setRequestProperty("Content-Language", "en-US");
		result.setUseCaches(false);
		return result;
	}
	
	private String getStringFromOpenConnection(HttpURLConnection connection) throws IOException {
		//Send request
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		//wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		//Get Response	
		InputStream is = connection.getInputStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		String line;
		StringBuffer response = new StringBuffer(); 
		while((line = rd.readLine()) != null) {
			response.append(line);
			response.append('\r');
		}
		rd.close();
		return response.toString();
	}

}
