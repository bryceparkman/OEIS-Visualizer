import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import processing.core.PApplet; 

public class Visualize extends PApplet{
	String seq;
	String vis;
	String[] list;
	public static int scale;
	public static void main(String[] args) {
		PApplet.main("Visualize");
	}
	public void settings() {
		size(600,600);
	}
	
	public void setup() {
		noLoop();
	}
	
	public void draw() {
		parseData();
		switch(vis) {
			case "NumberLine":
				new NumberLine(this, list); break; //Hops along the number line sequentially
			case "Binary":
				new Binary(this, list); break; //Creates drawing of binary representations of #s
			case "Graph":
				new Graph(this, list); break; //Simple graph
			case "Circles":
				new Circles(this ,list); break;
			case "Sounds":
				new Sounds(this, list); break;
			case "Path":
				new Path(this ,list); break;
			default:
				break;
		}
	}
	
	public void parseData() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					"C:\\Users\\bryce\\eclipse-workspace\\OEIS Visualizer\\src\\Params.txt"));
			seq = reader.readLine(); //Grab sequence #
			vis = reader.readLine(); //Grab type of visualizer
			scale = Integer.parseInt(reader.readLine()); //Grab scale
			reader.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		String json = jsonGetRequest("https://oeis.org/search?fmt=json&q=<" + seq + ">"); //Get oeis json
		list = json.substring(json.indexOf("data")+8,json.indexOf("\"", json.indexOf("data")+8)).split(",");
		
		//Gets data which is the list of integers in string format from json
	}

	
	//Thanks internet :))) its slow but it works
	  private static String streamToString(InputStream inputStream) {
	    @SuppressWarnings("resource")
		String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
	    return text;
	  }

	  public static String jsonGetRequest(String urlQueryString) {
	    String json = null;
	    try {
	      URL url = new URL(urlQueryString);
	      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	      connection.setDoOutput(true);
	      connection.setInstanceFollowRedirects(false);
	      connection.setRequestMethod("GET");
	      connection.setRequestProperty("Content-Type", "application/json");
	      connection.setRequestProperty("charset", "utf-8");
	      connection.connect();
	      InputStream inStream = connection.getInputStream();
	      json = streamToString(inStream); // input stream to string
	    } catch (IOException ex) {
	      ex.printStackTrace();
	    }
	    return json;
	  }
}
