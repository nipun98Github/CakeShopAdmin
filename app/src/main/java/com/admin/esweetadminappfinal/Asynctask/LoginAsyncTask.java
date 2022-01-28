package com.admin.esweetadminappfinal.Asynctask;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.admin.esweetadminappfinal.Appconfig.AppConfig;
import com.admin.esweetadminappfinal.Model.InvoiceO;
import com.admin.esweetadminappfinal.Utils.HttpPostClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class LoginAsyncTask extends AsyncTask<String, String, String> {

    private final View view;
    public LoginAsyncTask(View v) {
        this.view=v;
    }

    @Override
    protected String doInBackground(String... strings) {
        JsonObject jo = new JsonObject();

        jo.addProperty("email",strings[0]);
        jo.addProperty("invoiceID",strings[1]);


        String jsonText = jo.toString();

        List<BasicNameValuePair> lst = new ArrayList<>();
        lst.add(new BasicNameValuePair("parameter",jsonText));

        String response = new HttpPostClient().sendHttpPostRequest(AppConfig.hostUrl,lst);
        return response;
    }

    @Override

    protected void onPostExecute(String re) {
        super.onPostExecute(re);

        try {
            InvoiceO user = new Gson().fromJson(re,InvoiceO.class);
//            Log.d("keyyy",user.getKey());
            try {

                if(!user.getSt().equals("error")){
                    //intent

                }else{
                    Toast.makeText(view.getContext(),"Network Error !",Toast.LENGTH_LONG).show();
                }
            }catch(Exception e){
                Toast.makeText(view.getContext(),"Network Error !",Toast.LENGTH_LONG).show();

            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(view.getContext(), "network error", Toast.LENGTH_SHORT).show();
        }
    }
}
