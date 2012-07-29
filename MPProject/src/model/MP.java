package model;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MP {
	
	public static DBCollection getCollection() {
		// TODO Auto-generated constructor stub
		Mongo m = null;
		try {
			//m = new Mongo();
			m = new Mongo( "flame.mongohq.com" , 27051 );
			
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MongoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    DB db = m.getDB("indianparliament");
	    //Delete this when on localhost
	    char password[] = {'i','i','i','t','b'};
	    boolean auth = db.authenticate("iiitb", password);
	    
  		DBCollection mps = db.getCollection("mp");
  		
  		return mps;
	}
	
	public static DBCollection getLocalCollection() {
		// TODO Auto-generated constructor stub
		Mongo m = null;
		try {
			m = new Mongo();
			
			
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MongoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    DB db = m.getDB("indianparliament");
	    //Delete this when on localhost
	    //char password[] = {'i','i','i','t','b'};
	    //boolean auth = db.authenticate("iiitb", password);
	    
  		DBCollection mps = db.getCollection("mp");
  		
  		return mps;
	}
	
	public static ArrayList searchByName(String str)
	{
		DBCollection mps = getCollection();
		ArrayList mpList = new ArrayList();
		BasicDBObject query = new BasicDBObject();
  		query.put("Name", str);
  		DBCursor cur = mps.find(query);
  		while(cur.hasNext()) {
  			Object obj = cur.next();
  			//System.out.println(obj);
  			mpList.add(obj);
  			}
  		return mpList;
	}
	
	public static String getImageByName(String str)
	{
		DBCollection mps = getCollection();
		ArrayList mpList = new ArrayList();
		BasicDBObject query = new BasicDBObject();
  		query.put("Name", str);
  		DBCursor cur = mps.find(query);
  		while(cur.hasNext()) {
  			DBObject userdetails = cur.next();
  			Set fields = userdetails.keySet();
  			Iterator it = fields.iterator();
  			while(it.hasNext()){
  				String fieldName = (String) it.next();
  				if(fieldName.equalsIgnoreCase("image"))
  				{
  					return (String) userdetails.get(fieldName);
  				}
  			}
  		}
  		return null;
	}
	
	public static ArrayList searchByState(String str)
	{
		DBCollection mps = getCollection();
		ArrayList mpList = new ArrayList();
		BasicDBObject query = new BasicDBObject();
  		query.put("State Name", str);
  		DBCursor cur = mps.find(query);
  		while(cur.hasNext()) {
  			Object obj = cur.next();
  			//System.out.println(obj);
  			mpList.add(obj);
  			}
  		return mpList;
	}
	
	public static ArrayList searchByParty(String str)
	{
		DBCollection mps = getCollection();
		ArrayList mpList = new ArrayList();
		BasicDBObject query = new BasicDBObject();
  		query.put("Party Name", str);
  		DBCursor cur = mps.find(query);
  		while(cur.hasNext()) {
  			Object obj = cur.next();
  			//System.out.println(obj);
  			mpList.add(obj);
  			}
  		return mpList;
	}
	
	public static ArrayList searchByConsti(String str)
	{
		DBCollection mps = getCollection();
		ArrayList mpList = new ArrayList();
		BasicDBObject query = new BasicDBObject();
  		query.put("Constituency from which I am elected", str);
  		DBCursor cur = mps.find(query);
  		while(cur.hasNext()) {
  			Object obj = cur.next();
  			//System.out.println(obj);
  			mpList.add(obj);
  			}
  		return mpList;
	}
	
	public static ArrayList searchByDate(int min, int max)
	{
		DBCollection mps = getCollection();
		ArrayList mpList = new ArrayList();
  		DBCursor cur = mps.find();
  		while(cur.hasNext()) {
  			
  			DBObject userdetails = cur.next();
  			Set fields = userdetails.keySet();
  			Iterator it = fields.iterator();
  			while(it.hasNext()){
  				String fieldName = (String) it.next();
  				if(fieldName.equalsIgnoreCase("Date of Birth"))
  				{
  					String dateStr = (String)userdetails.get(fieldName);
  					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
  					try {
						Date date = sdf.parse(dateStr);
						Calendar today = GregorianCalendar.getInstance();
						Calendar dob = GregorianCalendar.getInstance();
						dob.setTime(date);
						int age=0;
						while(dob.after(today)==false){
						dob.add(Calendar.YEAR, 1);
						if (dob.after(today)==false){
						age++;
						}
						}
						if(age>min && age<max)
						{
							mpList.add(userdetails);
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
  				}
  			}
  			//System.out.println(obj);
  			//mpList.add(obj);
  			}
  		return mpList;
	}


}
