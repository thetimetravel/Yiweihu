package com.example.guy.yiweihu.Login;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.guy.yiweihu.R;

public class SearchByName extends AppCompatActivity  {
    HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    InputStream inputStream=null;
    Intent i;

    //public static final String recipes[]=new String[]{"Almond-Sheera","Bhel","Bread-Pizzas","Carrot-Pickle","Carrot-Relish"};
    String recipes[];
    EditText tv = (EditText) findViewById(R.id.editView);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login2);
        Button b2 = (Button) findViewById(R.id.button1);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                    httpclient = new DefaultHttpClient();
                    httppost = new HttpPost("http://127.0.0.1/zhiweibao/login/login.php");
                    response = httpclient.execute(httppost);    // Execute HTTP Post Request
                    inputStream = response.getEntity().getContent();
                    String result=null;

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"), 8);
                    StringBuilder sb = new StringBuilder();
                    String line=null;
                    while((line=reader.readLine())!= null){
                        sb.append(line+"\n");
                    }

                    inputStream.close();
                    result=sb.toString();

                    JSONArray jArray=new JSONArray(result);// this statment gives error

                    for(int i=0;i<jArray.length();i++){
                        JSONObject json=jArray.getJSONObject(i);
                        recipes[i]=json.getString("Username");

                    }

                }
                catch(Exception e){

                    Log.e("log_tag3", "Error convering result"+e.toString());

                }



            }
        });





}
}
