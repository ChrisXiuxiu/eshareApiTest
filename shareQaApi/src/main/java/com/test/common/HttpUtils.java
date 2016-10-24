package com.test.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class HttpUtils {
static CloseableHttpClient httpclient=null;
	
	public static void OpenHttpClient()
	{
		httpclient =  HttpClients.createDefault();
	}
	
	public static void CloseHttpClient()
	{
		try {
			httpclient.close();
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		httpclient = null;
	}

	public static JSONObject visitUrl(String url) throws ClientProtocolException, IOException
	{
		//CloseableHttpClient httpclient = HttpClients.createDefault();
		
		HttpGet httpGet = new HttpGet(url);
//		HttpPost httpPost = new HttpPost(url);
		JSONObject jsonObj=null;
		CloseableHttpResponse response = httpclient.execute(httpGet);
		try {
			
			HttpEntity entity = response.getEntity();
			
			StringBuilder jsonStr = new StringBuilder();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"),
					8 * 1024);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				jsonStr.append(line + "/n");
			}
			EntityUtils.consume(entity);
			jsonObj = new JSONObject(jsonStr.toString());
			response.close();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		
		return jsonObj;
	}
}
