package com.example.guy.yiweihu.Login;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.guy.yiweihu.R;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class loginactivity3 extends AppCompatActivity  {



    String result = null;

    private EditText editText;
    private   static   String s2;

    HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    InputStream inputStream=null;
    String recipes[];
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login2);
        Button b1 = (Button) findViewById(R.id.button1);
        editText=(EditText)findViewById(R.id.editView);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {

                        ArrayList<NameValuePair> nameValuePairs1=new ArrayList<NameValuePair>();




//                        try{
//                            JSONObject jsonObject=new JSONObject();
//                            jsonObject.put("name","tony");
//                            jsonObject.put("age",30);
//
//                          System.out.println("hh"+jsonObject.toString());
//
//
//                        }catch (JSONException e){
//                            e.printStackTrace();
//                        }

                        try {
                            Thread.sleep(1000);

                            httpclient = new DefaultHttpClient();
                            httppost = new HttpPost("http://192.168.2.202/zhiweibao/login/login.php");
                            response = httpclient.execute(httppost);    // Execute HTTP Post Request
                            inputStream = response.getEntity().getContent();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"), 8);
                            StringBuilder sb = new StringBuilder();
                            String line=null;
                            while((line=reader.readLine())!= null){
                                sb.append(line+"\n");
                            }

                            inputStream.close();
                            result=sb.toString();

                            JSONArray jArray=new JSONArray(result);// this statment gives error

                            List<String> list=new ArrayList<>();
                            System.out.println("length"+jArray);


                            for(int i=0;i<jArray.length();i++){
                                JSONArray json=jArray.getJSONArray(i);

                                for(int child=0;child<json.length();child++)
                                    list.add(json.get(child).toString());

                            }
                            System.out.print("成功"+list.get(1));
                            s2=list.get(0);

//                            StringBuffer sb1=new StringBuffer();
//                            for(String item:list){
//                                sb1.append(item);
//                            }
//                            String s1 = sb1.toString();
//                            System.out.print("成功s1"+s1);
//
//                            String[] arr=new String[list.size()];
//                            for(int i=0;i<list.size();i++){
//                                arr[i]=list.get(i);
//                            }
//
//                            StringBuffer sb2 = new StringBuffer();
//                            for(int i = 0; i < arr.length; i++){
//                                sb2.append(arr[i]);
//                            }
////                           s2 = sb2.toString();
//                            System.out.print("成功s"+s2);





                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("log_tag", "Error in http connection" + e.toString());
                        }


                    }
                }).start();
                editText.setText(s2);

            }

        });

//        b1.setOnClickListener(new Button.OnClickListener() {
//
//            @Override
//
//            public void onClick(View v) {
//
//                // TODO Auto-generated method stub
//
//                EditText tv = (EditText) findViewById(R.id.editView);
//
//                ArrayList<NameValuePair> nameValuePairs1=new ArrayList<NameValuePair>();
//                nameValuePairs1.add(new BasicNameValuePair("year","1970"));
//
//
//                try {
//
////                    HttpClient httpclient = new DefaultHttpClient();
////
////                    HttpGet httpget = new HttpGet(
////
////                            "http://127.0.0.1/zhiweibao/login/login.php");
////
////
////
////                    HttpResponse response = httpclient.execute(httpget);
////
////                    HttpEntity entity = response.getEntity();
////
////                    is = entity.getContent();
//
//                    httpclient = new DefaultHttpClient();
//                    httppost = new HttpPost("http://localhost/zhiweibao/login/login.php?");
//                    response = httpclient.execute(httppost);    // Execute HTTP Post Request
//                    inputStream = response.getEntity().getContent();
//                    String result=null;
//
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"), 8);
//                    StringBuilder sb = new StringBuilder();
//                    String line=null;
//                    while((line=reader.readLine())!= null){
//                        sb.append(line+"\n");
//                    }
//
//                    inputStream.close();
//                    result=sb.toString();
//
//                    JSONArray jArray=new JSONArray(result);// this statment gives error
//
//                    for(int i=0;i<jArray.length();i++){
//                        JSONObject json=jArray.getJSONObject(i);
//                        recipes[i]=json.getString("Username");
//
//                    }
//                    System.out.print("成功");
//                } catch (Exception e) {
//
//                    Log.e("log_tag", "Error in http connection" + e.toString());
//
//                }
//
//                // convert response to string
//
//                try {
//
//                    BufferedReader reader = new BufferedReader(
//
//                            new InputStreamReader(is, "iso-8859-1"), 8);
//
//                    sb = new StringBuilder();
//
//                    sb.append(reader.readLine() + "\n");
//
//
//
//                    String line = "0";
//
//                    while ((line = reader.readLine()) != null) {
//
//                        sb.append(line + "\n");
//
//                    }
//
//                    is.close();
//
//                    result = sb.toString();
//
//                } catch (Exception e) {
//
//                    Log.e("log_tag", "Error converting result " + e.toString());
//
//                }
//
//                // paring data
//
//                int ct_id;
//
//                String ct_name;
//
//                try {
//
//                    jArray = new JSONArray(result);
//
//
//                    JSONObject json_data = null;
//
//                    for (int i = 0; i < jArray.length(); i++) {
//
//                        json_data = jArray.getJSONObject(i);
//
//                        ct_id = json_data.getInt("Id");
//
//                        ct_name = json_data.getString("Username");
//                        tv.append("a");
//
//                        tv.append(ct_name + " \n");
//
//                    }
//
//                } catch (JSONException e1) {
//
//                    // Toast.makeText(getBaseContext(), "No City Found"
//
//                    // ,Toast.LENGTH_LONG).show();
//
//                } catch (ParseException e1) {
//
//                    e1.printStackTrace();
//
//                }
//
//            }
//
//        });
//


    }


}
