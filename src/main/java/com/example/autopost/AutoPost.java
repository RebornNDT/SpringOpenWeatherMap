package com.example.autopost;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Random;

import org.apache.http.client.ClientProtocolException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.staticvariable.FbToken;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.send.IdMessageRecipient;
import com.restfb.types.send.SendResponse;
//@Component
public class AutoPost {
	//@Scheduled( initialDelay = 600 * 1000, fixedDelay = 3 * 60 * 60 * 1000)
    public void postCurrentWheather() throws ClientProtocolException, URISyntaxException, IOException {
       System.out.println("Đang chuẩn bị post bài!");
       IdMessageRecipient recipient = new IdMessageRecipient(FbToken.USER_ID);
		//String pageAccessToken = FbToken.getPageToken(FbToken.LONG_LIVED_USER_TOKEN);
       String pageAccessToken = "EAAQBdEtHIX4BAIZB6hMX7huKVkyzjxdSU1ujTLKPTwooQd2fpOyPl007g040ZCjNGZBj4ZAhO8S23m1DOiwJW30uNcg1MZAz0YkLGavZB4s4mlDLdGqs1kdrAcQDwSadZCjugAjnBMU3JV6sAVG5EmoFh6aP71aQmWFxXAtaPTiz80eZC0ekfmCbwSXnSkVTxDYZD";
		FacebookClient pageClient = new DefaultFacebookClient(pageAccessToken, Version.VERSION_7_0);
		int temp = (int)(FbToken.get_current_wheather("Hanoi").getMain().getTemp() -273.15);
		int feel_like = (int)(FbToken.get_current_wheather("Hanoi").getMain().getFeels_like()-273.15);
		int pressure = FbToken.get_current_wheather("Hanoi").getMain().getPressure();
		int humidity = FbToken.get_current_wheather("Hanoi").getMain().getHumidity();
		Double speed_wind = FbToken.get_current_wheather("Hanoi").getWind().getSpeed();
		int visibility = (FbToken.get_current_wheather("Hanoi").getVisibility())/1000;
		//random mã để facebook cho phép post bài với nội dung giống nhau
		byte[] array = new byte[7]; // length is bounded by 7
	    new Random().nextBytes(array);
	    String generatedString = new String(array, Charset.forName("UTF-8"));
	    //Content post bài
		String content = "Thời tiết hiện tại ở Hà Nội: \n"
				+ "Nhiệt độ: " + temp + " độ C,\n"
				+ "Cảm giác như: "+feel_like+" độ C,\n"
				+ "Độ ẩm: "+humidity+" %,\n"
				+ "Áp suất: "+pressure+" Hpa,\n"
				+ "Tốc độ gió: "+speed_wind+" m/s,\n"
				+ "Tầm nhìn xa: "+visibility+" km,\n"
				+ "Random String: "+generatedString;
		SendResponse resp = pageClient.publish("110216847358764/feed", SendResponse.class,
		     Parameter.with("recipient", recipient), // the id or phone recipient
			 Parameter.with("message", content));
		System.out.println("Post bài thành công!");
    }
}
