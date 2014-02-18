package test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//getTitle("http://www.oracle.com");
		
		getLinks("http://www.oracle.com");
		
		getImages("http://www.oracle.com");
	}
	
	private static void getTitle(String url)
	{
		Document doc = null;
		try {
			System.setProperty("http.proxyHost", "www-proxy.us.oracle.com");
			System.setProperty("http.proxyPort", "80");
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String title = doc.title();
		System.out.println("title:"+title);
	}
	
	private static void getLinks(String url)
	{
		Document doc = null;
		try {
			System.setProperty("http.proxyHost", "www-proxy.us.oracle.com");
			System.setProperty("http.proxyPort", "80");
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Elements links = doc.select("a[href]");
		for(Element link: links)
		{
			System.out.println("link-->"+link.attr("abs:href"));
		}
	}
	
	private static void getImages(String url)
	{
		Document doc = null;
		
		System.setProperty("http.proxyHost", "www-proxy.us.oracle.com");
		System.setProperty("http.proxyPort", "80");
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements images = doc.select("img");
		
		for(Element image:images)
		{
			System.out.println("image location-->"+image.attr("abs:src"));
		}
	}

}
