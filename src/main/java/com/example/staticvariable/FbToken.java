package com.example.staticvariable;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.example.JsonObj.ResponseObjCurrent;
import com.example.JsonObj.TokenPageObj;
import com.google.gson.Gson;

public class FbToken {
	//token này sống trong 60 ngày tính từ 15-05-2020
	public static String LONG_LIVED_USER_TOKEN = "EAAQBdEtHIX4BAC6Tk46ttUCKi2c9lyrBcDLsK2az0rwVGNQLchVgfrYZCiOIzCASQy0DzgcjxGosiC03q50ZBpJ7KpQrhRL9uGuk5G1dDrizu2fZC7wap2CR373BZCErqgmkIGNgR2uEQycEKqM1rr1YCZBZBbvyI77dlA8YNRiuGlicXUDxJi";
	//ID của User Lê Đức
	public static String USER_ID = "103313298057613";
	//ID của fanpage "Cập nhât thời tiết Hà Nội"
	public static String PAGE_ID = "110216847358764";
	//public static String LONG_LIVED_PAGE_TOKEN = "EAAQBdEtHIX4BALE3SHqA7KkZCLCisJLONHZAZCsRDPwoslmAtEZCU37xwGrWR7bhMy4eZA2PX898m1h7KysizWzKX8nhZCZCOsPq8qYARKZAd55yJE9RfZB12ik2CiMcryLegIohDAH7kMZAwnzjSm9md23xDGYLPrBAZCtkWY4KphF8k5smQsjLkT9SZBl9ZCD6fLOop8ZAQK7MwOSI7qH9TRbWgg";
	//Sinh Page_Access_Token từ User_token
	public static String getPageToken(String user_token) throws URISyntaxException, ClientProtocolException, IOException {
		HttpClient httpclient = HttpClients.createDefault();
        URIBuilder builder = new URIBuilder("https://graph.facebook.com/110216847358764");
        builder.setParameter("fields","access_token");
        builder.setParameter("access_token", LONG_LIVED_USER_TOKEN);
        URI uri = builder.build();
        HttpGet request = new HttpGet(uri);
        HttpResponse response = httpclient.execute(request);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);
        Gson gson = new Gson();
        TokenPageObj tokenPageObj = gson.fromJson(result, TokenPageObj.class);
        return tokenPageObj.getAccess_token();
	}
	//lấy dữ liệu thời tiết để gửi lên fanpages
	public static ResponseObjCurrent get_current_wheather(String city) throws URISyntaxException, ClientProtocolException, IOException {
		HttpClient httpclient = HttpClients.createDefault();
        URIBuilder builder = new URIBuilder("https://api.openweathermap.org/data/2.5/weather");
        builder.setParameter("q", city);
        builder.setParameter("appid", "973409c76a23124126ad59dcfd692ba3");
        URI uri = builder.build();
        HttpPost request = new HttpPost(uri);
        HttpResponse response = httpclient.execute(request);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);
        Gson gson = new Gson();
        ResponseObjCurrent current = gson.fromJson(result,ResponseObjCurrent.class);
        return current;
	}
}
