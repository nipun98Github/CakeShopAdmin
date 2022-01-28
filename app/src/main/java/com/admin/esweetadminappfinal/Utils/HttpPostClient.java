package com.admin.esweetadminappfinal.Utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class HttpPostClient {

    public String sendHttpPostRequest(String url, List<BasicNameValuePair> data) {
        String response = "";
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url);
// request send to servelet
            postRequest.setEntity(new UrlEncodedFormEntity(data));

//            get response
            HttpResponse httpResponse = httpClient.execute(postRequest);
            if (httpResponse.getEntity() != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response = response + line;
                }


            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return response;
    }
}
