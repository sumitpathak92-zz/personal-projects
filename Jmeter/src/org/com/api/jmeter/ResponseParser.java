package org.com.api.jmeter;

import java.io.File;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ResponseParser {

	protected JSONArray response() throws IOException {
		File input = new File("create_report.xml");
		Document doc = Jsoup.parse(input, "UTF-8","report.xml");
		Elements e = doc.getElementsByTag("responseData");
		String text = null;
		JSONObject obj = null;
		JSONArray js = new JSONArray();
		for(Element response : e)
		{
			text = response.text();
			obj = new JSONObject(text);
			js.put(obj);
		}
		//		System.out.println(js);
		return js;
	}

	protected int singleObject(JSONObject jobj) {
		int id = (int) jobj.get("id");
//		System.out.println(id);
		return id;
	}
	
	protected JSONObject cleanObject(JSONObject jobj)
	{
		jobj.remove("logo_url");
		jobj.remove("keyword_count");
		jobj.remove("crawling_status");
		jobj.remove("custom_ctr");
		jobj.remove("crawled_dt");
		jobj.remove("sub_domain_count");
		jobj.remove("theme_count");
		int id = singleObject(jobj);
		jobj.put("id", String.valueOf(id));
		jobj.put("crawling_frequency", "daily");
		jobj.put("domain_url", "http://www.put.com/wtaf");
		return jobj;
		
	}
}
