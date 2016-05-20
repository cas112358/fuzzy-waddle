import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Searcher {
	
	public String[] search(String searchTerm){
		String site = "http://www.google.com/search?q=";
		String search = "site:http://stackoverflow.com/questions Java" + searchTerm;
		String charset = "UTF-8";
		String userAgent = "ExampleBot 1.0 (+http://example.com/bot)"; // Change this to your company's name and bot homepage!
		org.jsoup.select.Elements links = null;
		try {
			links = Jsoup.connect(site + URLEncoder.encode(search, charset)).userAgent(userAgent).get().select(".g>.r>a");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("FAIL");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("FAIL");
		}

		String[] relURL = new String[links.size()];
		int count = 0; 

		for (Element link : links) {
			String url = link.absUrl("href"); // Google returns URLs in format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
			try {
				url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("FAIL");
			}

			if (!url.startsWith("http")) {
				continue; // Ads/news/etc.
			}

			relURL[count] = url;
			count++;
		}

		return relURL;

	}

	public ArrayList<String> parseHTML(String URL){			
		Document doc = null;
		String htmlCode = null;

		try {
			htmlCode = Jsoup.connect(URL).userAgent("Mozilla").get().html();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<String> codeSnips = new ArrayList<String>();

		doc = Jsoup.parse(htmlCode);

		Elements p = doc.getElementsByTag("code");

		for (Element x: p) {
			codeSnips.add(x.text());
		}

		return codeSnips;
	}	
}
