import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sun.org.mozilla.javascript.internal.json.JsonParser;

public class GetMP {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Timer t = new GetMP().new Timer();
		getMPLinks("http://164.100.47.132/LssNew/Members/Alphabaticallist.aspx");
		//t.endTimer();
	}
	
	public class Timer {
		
		private long startTime = 0L;
		private long endTime = 0L;
		
		public Timer(){
			startTime = System.currentTimeMillis();
		}
		
		public void endTimer()
		{
			endTime = System.currentTimeMillis();
			System.out.println("total time:"+(endTime-startTime)/1000);
		}
		
		public void endTimer(String methodName)
		{
			endTime = System.currentTimeMillis();
			System.out.println(methodName+"took:"+(endTime-startTime)/1000);
		}
	}
	
	
	private static void getMPLinks(String url)
	{
		Document doc = null;
		try {
			System.setProperty("http.proxyHost", "www-proxy.us.oracle.com");
			System.setProperty("http.proxyPort", "80");
			doc = Jsoup.connect(url).timeout(0).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Elements links = doc.select("a[href^=Biography.aspx?]");
		ArrayList<String> mpsnoList = new ArrayList<String>();
		for(Element link: links)
		{
			String linkString = link.attr("abs:href");
			//System.out.println("link-->"+link.attr("abs:href"));
			String mpsNoString = linkString.substring(linkString.indexOf("=")+1);
			//System.out.println("mpsNoString-->"+mpsNoString);
			mpsnoList.add(mpsNoString);
		}
		
		//getMPDetails((String)mpsnoList.get(0));
		//storeMPDetails((String)mpsnoList.get(0));
		//storeMPDetails("28");
		
		int count = 1;
		
		try {
			FileWriter file = new FileWriter("mpfiles/mps.json");
			
			JSONArray mpsList = new JSONArray();
			
			for(String mpsno:mpsnoList)
			{
				storeMPDetails(mpsno,mpsList);
				System.out.println("Done with "+count+" MPs");
				count++;
			}
			
			file.write(mpsList.toJSONString());
			file.flush();
			file.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/*private static void getMPDetails(String mpNo)
	{
		long startTime = System.currentTimeMillis();
		Document doc = null;
		try {
			System.setProperty("http.proxyHost", "www-proxy.us.oracle.com");
			System.setProperty("http.proxyPort", "80");
			String url = "http://164.100.47.132/LssNew/Members/Biography.aspx?mpsno=" + mpNo;
			doc = Jsoup.connect(url).timeout(0).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		getValueFromLabel(doc,  "Email Address :");
		getValueFromLabel(doc,  "Party Name");
		getValueFromLabel(doc,  "Constituency");
		getValueFromLabel(doc,  "Father's Name");
		long endTime = System.currentTimeMillis();
		System.out.println("total time:"+(endTime-startTime)/1000);
		
	}*/
	
	private static void storeMPDetails(String mpNo, JSONArray mpsList)
	{
		
		Document doc = null;
		try {
			System.setProperty("http.proxyHost", "www-proxy.us.oracle.com");
			System.setProperty("http.proxyPort", "80");
			String url = "http://164.100.47.132/LssNew/Members/Biography.aspx?mpsno=" + mpNo;
			doc = Jsoup.connect(url).timeout(0).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		storeInJson(mpsList, doc, mpNo, "Email Address :","Party Name","Constituency","Father's Name","Mother's Name","Date of Birth","Place of Birth","Marital Status",
				"Date of Marriage", "Spouse's Name", "No. of Sons", "No.of Daughters", "Educational Qualifications", "Profession","Permanent Address","Present Address",
				"Positions Held","Literary Artistic & Scientific Accomplishments","Social And Cultural Activities","Special Interests","Favourite Pastime and Recreation","Sports and Clubs","Countries Visited","Other Information");
		
		//storeInJson(doc, mpNo, "Social And Cultural Activities");
		
	}
	
	private static void storeInJson(JSONArray mpsList, Document doc, String mpNo, String... args)
	{
		//Timer t = new GetMP().new Timer();
		try {
		//FileWriter file = new FileWriter("mpfiles/mps.json");
		JSONObject obj = new JSONObject();
		boolean found = false;
		
		
		obj.put("candidateId", mpNo);
		
		found = storeName(doc,obj);
		found = storeImage(doc,obj);
		for(String label:args)
		{
			
			if(label.equals("Literary Artistic & Scientific Accomplishments") ||
					label.equals("Social And Cultural Activities") ||
					label.equals("Special Interests") ||
					label.equals("Favourite Pastime and Recreation") ||
					label.equals("Sports and Clubs") ||
					label.equals("Countries Visited") ||
					label.equals("Other Information"))
			{
				found = storeOtherInfo(doc, label,obj);
				continue;
			}
			if(label.equals("Positions Held"))
			{
				found = storePositionsHeld(doc, label,obj);
				continue;
			}
			
			String value = null;
			Elements temps = doc.select(":containsOwn("+label+")");
			if(temps!=null)
			{
				for(Element temp: temps)
				{
					
					Element actualElement = temp.nextElementSibling();
					if(label.equals("Permanent Address") || label.equals("Present Address"))
					{
						found = storeAddress(actualElement, label,obj);
						continue;
					}
					if(actualElement!=null)
					{
						value = actualElement.ownText();
					    //System.out.println(label+":"+value);
						obj.put(label, value);
						found = true;
					}
						
				}
			}
		}
		
		mpsList.add(obj);
		
		}finally{
			//t.endTimer("storeInJson");
		}
		
	}
	
	private static boolean storeName(Document doc, JSONObject obj)
	{
		String value = "";
		Elements profileElements = doc.select("#ctl00_ContPlaceHolderMain_Bioprofile1_Datagrid1");
		boolean found = false;
		if(profileElements!=null)
		{
			for(Element profileElement: profileElements)
			{
				if(profileElement!=null)
				{
					Elements headerElements = profileElement.select(".gridheader1");
					for(Element headerElement: headerElements)
					{
						value = headerElement.ownText();
						found = true;
						break;
					}
				}
					
			}
		}

		if(found)
		{
			//System.out.println("Name:"+value);
			obj.put("Name", value);
		}
		
		return found;
		
	}
	
	private static boolean storeImage(Document doc, JSONObject obj)
	{
		String value = "";
		Elements actualElements = doc.select("#ctl00_ContPlaceHolderMain_Bioprofile1_Image1");
		boolean found = false;
		if(actualElements!=null)
		{
			for(Element actualElement: actualElements)
			{
				if(actualElement!=null)
				{
					value = actualElement.attr("src");
					found = true;
					break;
				}
					
			}
		}

		if(found)
		{
			//System.out.println("Image:"+value);
			obj.put("Image", value);
		}
		
		return found;
		
	}
	
	private static boolean storeOtherInfo(Document doc, String label, JSONObject obj)
	{
		String value = "";
		Elements profileElements = doc.select("#ctl00_ContPlaceHolderMain_Bioprofile1_Datagrid4");
		boolean found = false;
		if(profileElements!=null)
		{
			for(Element profileElement: profileElements)
			{
				if(profileElement!=null)
				{
					Elements headerElements = profileElement.select(":containsOwn("+label+")");
					for(Element headerElement: headerElements)
					{
						Element actualElement = headerElement.parent().nextElementSibling().child(0);
						if(actualElement!=null)
						{
							value += actualElement.ownText();
							found = true;
							break;
						}
					}
				}
					
			}
		}

		if(found)
		{
			//System.out.println(label+":"+value);
			obj.put(label, value);
		}
		
		return found;
		
	}
	
	private static boolean storePositionsHeld(Document doc, String label, JSONObject obj)
	{
		String value = "";
		Elements profileElements = doc.select("#ctl00_ContPlaceHolderMain_Bioprofile1_Datagrid3");
		boolean found = false;
		int count = 0;
		if(profileElements!=null)
		{
			for(Element profileElement: profileElements)
			{
				if(profileElement!=null)
				{
					count = 0;
					Elements actualElements = profileElement.select("td.griditem2");
					for(Element actualElement: actualElements)
					{
						if(actualElement!=null)
						{
							if(count==0	&& actualElement.ownText().equals(""))
							{
								count++;
							}
							else if(count==0 && !actualElement.ownText().equals(""))
							{
								value += "\n"+actualElement.ownText()+"\n";
								count++;
							}
							else if(count==1)
							{
								value += actualElement.ownText()+"\n";
								count=0;
							}
								
							found = true;
						}
					}
				}
					
			}
		}

		if(found)
		{
			//System.out.println(label+":"+value);
			obj.put(label, value);
		}
		
		return found;
		
	}
	
	
	private static boolean storeAddress(Element root, String label, JSONObject obj)
	{
		String value = "";
		Elements actualElements = root.select("td");
		boolean found = false;
		if(actualElements!=null)
		{
			for(Element actualElement: actualElements)
			{
				if(actualElement!=null)
				{
					value += actualElement.ownText()+"\n";
					found = true;
				}
					
			}
		}

		if(found)
		{
			//System.out.println(label+":"+value);
			obj.put(label, value);
		}
		
		return found;
		
	}
	
	/*private static String getValueFromLabel(Document doc, String label)
	{
		
		String value = null;
		Elements temps = doc.select(":containsOwn("+label+")");
		for(Element temp: temps)
		{
			Element actualElement = temp.nextElementSibling();
			value = actualElement.ownText();
			System.out.println("value-->"+value);
		}
		return value;
	}*/

}
