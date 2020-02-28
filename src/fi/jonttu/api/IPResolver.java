package fi.jonttu.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class IPResolver {

	private static final String token = "TOKEN";
	private static final String url = "https://api.jonttu.fi/ip.php?token=%token%&ip=%ip%";
	
	private String status = "no data";
	private String ip = "?";
	private String country = "?";
	private String city = "?";
	private String isp = "?";
	private boolean hosting = false;
	
	public boolean getStatus() { return status.equals("success"); }
	public String getStatusMsg() { return status; }
	public String getIP() { return ip; }
	public String getCountry() { return country; }
	public String getCity() { return city; }
	public String getIsp() { return isp; }
	public boolean isHosting() { return hosting; }
	
	public IPResolver(String ip) {
		try {
			InputStream is = new URL(url.replace("%token%", token).replace("%ip%", ip)).openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = rd.read()) != -1) {
		      sb.append((char) cp);
		    }
			JsonObject json = new JsonParser().parse(sb.toString()).getAsJsonObject();
			status = json.get("status").getAsString();
			if (status.equals("success")) {
				this.ip = json.get("address").getAsString();
				this.country = json.get("country").getAsString();
				this.city = json.get("city").getAsString();
				this.isp = json.get("isp").getAsString();
				this.hosting = json.get("hosting").getAsBoolean();
			}
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
