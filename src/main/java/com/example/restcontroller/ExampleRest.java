package com.example.restcontroller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.classbean.Schedule;

@RestController
public class ExampleRest {
	@GetMapping(path = "/test-01",produces = "application/json")
	public String getRawJson(@RequestParam("location") String city) throws URISyntaxException, ClientProtocolException, IOException {
		HttpClient httpclient = HttpClients.createDefault();

        
            URIBuilder builder = new URIBuilder("https://api.openweathermap.org/data/2.5/weather");

            builder.setParameter("q", city);
            builder.setParameter("appid", "973409c76a23124126ad59dcfd692ba3");
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
//            request.setHeader("Content-Type", "application/json");
//            request.setHeader("Ocp-Apim-Subscription-Key", "2584c2d2b3424fd9a1f589710b124690");

//            String requestBody = "{ \"url\": \"https://image2.tienphong.vn/w665/Uploaded/2020/bpivpvoi/2019_11_16/ngoc_trinh_2_gygb.jpg\" }";
            // Request body
//            StringEntity reqEntity = new StringEntity(requestBody);
//            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

//            if (entity != null) 
//            {
//                System.out.println(EntityUtils.toString(entity));
//                System.out.println(response.getStatusLine().getStatusCode());
//                System.out.println(response.getEntity());
//            }
            String result = EntityUtils.toString(entity);
            return result;
	}
	@PostMapping("/setSchedule")
	public String setTimeAutoPost(@RequestParam(name = "time", defaultValue = "3") String time) {
		Schedule.fixedRate = Integer.valueOf(time) * 60 * 60 * 1000;
		return "set time auto post facebook mỗi "+time+" tiếng thành công!";
	}
}
