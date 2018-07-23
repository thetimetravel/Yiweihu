package com.example.guy.yiweihu.Login;



import android.net.ParseException;
import android.os.Bundle;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.guy.yiweihu.R;


public class loginactivity2 extends AppCompatActivity  {



    String result = null;

    private EditText editText;
    private   static   String s2;

    HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    InputStream inputStream=null;
    String recipes[];
    private final static String TAG = "UOfly Android Thread ==>";
    private int count = 0;
    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        public void run() {

            count++;
            setTitle("" + count);
            // 每3秒执行一次
            mHandler.postDelayed(mRunnable, 3000);  //给自己发送消息，自运行
        }
    };
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



                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("log_tag", "Error in http connection" + e.toString());
                        }


                    }
                }).start();
                editText.setText(s2);

            }

        });



    }



}
