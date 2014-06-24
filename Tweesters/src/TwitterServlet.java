import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.IOException;
import java.io.Reader;

import javax.servlet.http.*;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import sun.net.www.http.HttpClient;

/*import com.google.appengine.api.datastore.DatastoreService;
 import com.google.appengine.api.datastore.DatastoreServiceFactory;
 import com.google.appengine.api.datastore.Entity;
 import com.google.appengine.api.datastore.Key;
 import com.google.appengine.api.datastore.KeyFactory;
 import com.google.appengine.api.datastore.Query.Filter;
 import com.google.appengine.api.datastore.Query.FilterPredicate;
 import com.google.appengine.api.datastore.Query.FilterOperator;
 import com.google.appengine.api.datastore.Query.CompositeFilter;
 import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
 import com.google.appengine.api.datastore.Query;
 import com.google.appengine.api.datastore.PreparedQuery;
 import com.google.appengine.api.datastore.Entity;
 import com.google.appengine.api.datastore.Query.SortDirection;*/
@SuppressWarnings("serial")
public class TwitterServlet extends HttpServlet {
	HashMap<String, String> getSmiley = new HashMap<String, String>();
	HashMap<String, String> setPosition = new HashMap<String, String>();
	List<String> categories = new ArrayList<String>();
	List<String> states = new ArrayList<String>();
	String nexturl;

	public TwitterServlet() {

		getSmiley.put("Neutral", ":|");
		getSmiley.put("VeryPositive", ":o");
		getSmiley.put("Positive", ":)");
		getSmiley.put("Negative", ":(");
		getSmiley.put("VeryNegative", ":'(");
		setPosition.put("Alabama", "32.806673,-86.791133,125mi");
		setPosition.put("Alaska", "61.370717,-152.404420,125mi");
		setPosition.put("Arizona", "33.729761,-111.431224,125mi");
		setPosition.put("Arkansas", "34.969705,-92.373124,125mi");
		setPosition.put("California", "36.116203,-119.681563,125mi");
		setPosition.put("Colorado", "39.059810,-105.311105,125mi");
		setPosition.put("Connecticut", "41.597781,-72.755369,125mi");
		setPosition.put("Delaware", "39.318522,-75.507138,125mi");
		setPosition.put("District of Columbia", "38.897439,-77.026816,125mi");
		setPosition.put("Florida", "27.766279,-81.686782,125mi");
		setPosition.put("Georgia", "33.040618,-83.643072,125mi");
		setPosition.put("Hawaii", "21.094319,-157.498339,125mi");
		setPosition.put("Idaho", "44.240460,-114.478825,125mi");
		setPosition.put("Illinois", "40.349455,-88.986137,125mi");
		setPosition.put("Indiana", "39.849425,-86.258278,125mi");
		setPosition.put("Iowa", "42.011538,-93.210526,125mi");
		setPosition.put("Kansas", "38.526599,-96.726489,125mi");
		setPosition.put("Kentucky", "37.668141,-84.670064,125mi");
		setPosition.put("Louisiana", "31.169546,-91.867805,125mi");
		setPosition.put("Maine", "44.693948,-69.381924,125mi");
		setPosition.put("Maryland", "39.063944,-76.802101,125mi");
		setPosition.put("Massachusetts", "42.230173,-71.530104,125mi");
		setPosition.put("Michigan", "43.326618,-84.536093,125mi");
		setPosition.put("Minnesota", "45.694455,-93.900189,125mi");
		setPosition.put("Mississippi", "32.741647,-89.678697,125mi");
		setPosition.put("Missouri", "38.456087,-92.288368,125mi");
		setPosition.put("Montana", "46.921924,-110.454351,125mi");
		setPosition.put("Nebraska", "41.125370,-98.268081,125mi");
		setPosition.put("Nevada", "38.313513,-117.055372,125mi");
		setPosition.put("New Hampshire", "43.452491,-71.563899,125mi");
		setPosition.put("New Jersey", "40.298904,-74.521013,125mi");
		setPosition.put("New Mexico", "34.840514,-106.248483,125mi");
		setPosition.put("New York", "42.165724,-74.948052,125mi");
		setPosition.put("North Carolina", "35.630066,-79.806417,125mi");
		setPosition.put("North Dakota", "47.528910,-99.784012,125mi");
		setPosition.put("Ohio", "40.388781,-82.764916,125mi");
		setPosition.put("Oklahoma", "35.565342,-96.928919,125mi");
		setPosition.put("Oregon", "44.572020,-122.070939,125mi");
		setPosition.put("Pennsylvania", "40.590752,-77.209755,125mi");
		setPosition.put("Rhode Island", "41.680893,-71.511782,125mi");
		setPosition.put("South Carolina", "33.856893,-80.945011,125mi");
		setPosition.put("South Dakota", "44.299782,-99.438825,125mi");
		setPosition.put("Tennessee", "35.747845,-86.692343,125mi");
		setPosition.put("Texas", "31.054487,-97.563460,125mi");
		setPosition.put("Utah", "40.150032,-111.862433,125mi");
		setPosition.put("Vermont", "44.045877,-72.710689,125mi");
		setPosition.put("Virginia", "37.769335,-78.169968,125mi");
		setPosition.put("Washington", "47.400902,-121.490493,125mi");
		setPosition.put("West Virginia", "38.491226,-80.954452,125mi");
		setPosition.put("Wisconsin", "44.268544,-89.616509,125mi");
		setPosition.put("Wyoming", "42.755965,-107.302488,125mi");
		categories.add("Music");
		categories.add("Sports");
		
		  categories.add("Entertainment"); categories.add("Twitter");
		 categories.add("funny"); categories.add("fashion");
		  categories.add("family"); categories.add("technology");
		  categories.add("food"); categories.add("drink");
		  categories.add("news"); categories.add("art");
		  categories.add("design"); categories.add("books");
		  categories.add("buisness"); categories.add("health");
		  categories.add("travel"); categories.add("government");
		 	categories.add("television"); categories.add("nba");
		  categories.add("nascar"); categories.add("faith");
		  categories.add("religion"); categories.add("nhc");
		 
		states.add("Alabama");
		states.add("Alaska");
		
		  states.add("Arizona"); states.add("Arkansas");
		  states.add("California"); states.add("Colorado");
		  states.add("Connecticut"); states.add("District of Columbia");
		  states.add("Florida"); states.add("Georgia"); states.add("Hawaii");
		  states.add("Idaho"); states.add("Illinois"); states.add("Indiana");
		  states.add("Iowa"); states.add("Kansas"); states.add("Kentucky");
		  states.add("Louisiana"); states.add("Maine"); states.add("Maryland");
		  states.add("Massachusetts"); states.add("Michigan");
		  states.add("Minnesota"); states.add("Mississippi");
		  states.add("Missouri"); states.add("Montana");
		  states.add("Nebraska"); states.add("Nevada");
		  states.add("New Hampshire"); states.add("New Jersey");
		  states.add("New Mexico"); states.add("New York");
		  states.add("North Carolina"); states.add("North Dakota");
		  states.add("Ohio"); states.add("Oklahoma"); states.add("Oregon");
		  states.add("Pennsylvania"); states.add("Rhode Island");
		  states.add("South Carolina"); states.add("South Dakota");
		  states.add("Tennessee"); states.add("Texas"); states.add("Utah");
		  states.add("Vermont"); states.add("Virginia");
		 states.add("Washington"); states.add("West Virginia");
		 states.add("Wisconsin"); states.add("Wyoming");
		 

	}

	public int sentimentVal;
	public String mood;
	public String smiley;
	HashMap<String, Double> sentimentWordMap = new HashMap<String, Double>();
	final String query = "http://search.twitter.com/search.json?q=";
	final String query1 = "http://search.twitter.com/search.json";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		// resp.getWriter().println("Hello tester");
		resp.setContentType("text/html");
		for (int m = 0; m < states.size(); m++) {

			String location = states.get(m);
			resp.getWriter().println(location + "\n");
			String geo = setPosition.get(location);
			String geop = geo.replace(",", "%2C");
			for (int k = 0; k < categories.size(); k++) {
				StringBuilder buildUrl = new StringBuilder();
				String type = categories.get(k);
				buildUrl.append(query).append(type).append("&geocode=")
						.append(geop).append("&rpp=").append("100");
				URL url = new URL(buildUrl.toString());
				resp.getWriter().println(url);

				for (int l = 0; l <= 10; l++) {
					resp.getWriter().println("counter:" + l + "\n");
					try {
						Gson gson = new Gson();
						BufferedReader reader;
						SearchResponse response;
						if (l == 0) {
							reader = new BufferedReader(new InputStreamReader(
									url.openStream()));
							resp.getWriter()
									.println(
											"I am in 0xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
						} else {
							StringBuilder sru = new StringBuilder();
							sru.append(query1).append(nexturl);
							URL url1 = new URL(sru.toString());
							resp.getWriter().println(url1);

							reader = new BufferedReader(new InputStreamReader(
									url1.openStream()));
							resp.getWriter().println(
									"I am in yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy"
											+ l);
						}
						BufferedReader finnReader = new BufferedReader(
								new InputStreamReader(TwitterServlet.class
										.getResourceAsStream("afinn.json")));
						resp.getWriter().println(
								"I am in cccccccccccccccccccccccccccccccccccccccccccccc"
										+ l);
						sentimentWordMap = gson.fromJson(finnReader,
								(new HashMap<String, Integer>()).getClass());

						resp.getWriter().println(
								"I am in aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
										+ l);
						response = gson.fromJson(reader, SearchResponse.class);
						resp.getWriter().println(
								"I am in bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"
										+ l);
						nexturl = response.nextPage;

						List<Result> results = response.results;
						List<DataObject> twit = new ArrayList<DataObject>();
						if (!results.isEmpty()) {
							for (Result result : results) {
								DataObject db = new DataObject();
								sentimentVal = calSentiments(result.text);
								mood = calMood(sentimentVal);
								smiley = getSmileys(mood);
								// db.setCreatedAt(result.createdAt.toString());
								// db.setFrom_user(result.fromUser);
								db.created_at = result.createdAt.toString();
								db.from_user = result.fromUser;
								db.from_userid = result.from_user_id;
								db.from_username = result.fromUserName;
								// db.geo=result.geo;
								db.langa = result.isoLanguageCode;
								db.profileimageurl = result.profileImageUrl;
								db.retweetcount = result.metadata.retweetcount;
								db.tid = result.id;
								db.tweet = result.text;
								db.loc = location;
								db.catso = type;
								db.sentival = String.valueOf(sentimentVal);
								db.sentitype = mood;
								db.sentiIcon = smiley;
								// db.setData(result.createdAt.toString(),result.fromUser,result.from_user_id,
								// result.fromUserName,result.geo,result.id,
								// result.isoLanguageCode,result.metadata.retweetcount,result.profileImageUrl,result.text);
								// resp.getWriter().println("<br/>"+"<img src="+result.profileImageUrl+" alt="+"check"+">"+"userid:"+result.from_user_id+" "+"tweet:"+result.text+" "+"Username:"+result.fromUser+" "+"Mood:"+mood+" "+"val:"+sentimentVal+" "+smiley+" "+"Popularitycount:"+result.metadata.retweetcount+"<br/>");
								twit.add(db);
								// resp.getWriter().println(db.getData());
								// tweetsList.add(db.getData());

							}

						} else {
							continue;
						}
						StringBuilder tweetResultBuilder = new StringBuilder();
						for (int j = 0; j < twit.size(); j++) {

							tweetResultBuilder.append(gson.toJson(twit.get(j)))
									.append("\n");
						}
						FileWriter writer = new FileWriter(
								"D:\\verybigfile.json", true);

						writer.write(tweetResultBuilder.toString());
						writer.close();

						// resp.getWriter().print(tweetResultBuilder);

						// resp.getWriter().print("values221212:"+sentimentWordMap.get("yes").intValue());
						reader.close();
						finnReader.close();
					}

					catch (Exception e) {
						resp.getWriter().print(e);
					}

				}
			}
		}
	}

	public int calSentiments(String text) {
		Pattern wordPattern = Pattern.compile("\\w+('\\w*)?");
		int totalSentimentValue = 0;
		Matcher matcher = wordPattern.matcher(text);
		while (matcher.find()) {
			String matchedWord = matcher.group();
			Double sentimentValue = sentimentWordMap.get(matchedWord);
			if (sentimentValue == null) {
				sentimentValue = (double) 0;
			} else {
				totalSentimentValue += sentimentValue;
			}
		}
		return totalSentimentValue;
	}

	public Sentiments type = Sentiments.Neutral;

	private String calMood(int sentimentValue) {
		if (sentimentValue == 0) {
			type = Sentiments.Neutral;
		} else if (sentimentValue > 3) {
			type = Sentiments.VeryPositive;
		} else if (sentimentValue > 0 && sentimentValue <= 3) {
			type = Sentiments.Positive;
		} else if (sentimentValue < 0 && sentimentValue >= -3) {
			type = Sentiments.Negative;
		} else {
			type = Sentiments.VeryNegative;
		}
		return type.toString();
	}

	private String getSmileys(String sentiment) {
		return getSmiley.get(sentiment);
	}
}
