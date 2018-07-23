package com.example.guy.yiweihu.okhttp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.guy.yiweihu.R;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class OkActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_get;
    private Button btn_post;
    private TextView tv_result;
//    private Handler handler;
     private  OkHttpClient client=new  OkHttpClient();
    private static final int GET = 1;
    /**
     * post请求
     */
    private static final int POST = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ok_main);
        btn_get = (Button) findViewById(R.id.btn_get);
        btn_post = (Button) findViewById(R.id.btn_post);
        tv_result = (TextView) findViewById(R.id.tv_result);
        //设置点击事件
        btn_get.setOnClickListener(this);
        btn_post.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_get:
                getDataFromByGet();
                break;
            case R.id.btn_post:
                getDataFromByPost();
                break;
        }
    }

    private void getDataFromByGet() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    String resutl = get("http://192.168.2.202/zhiweibao/login/login.php");
                    Message msg = Message.obtain();
                    msg.what = GET;
                    msg.obj = resutl;
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private  String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    private void getDataFromByPost() {
        new Thread(){
            @Override
            public void run() {
                super.run();

                String resutl = null;
                try {
                    resutl = post("http://api.m.mtime.cn/PageSubArea/TrailerList.api","");
                    Message msg = Message.obtain();
                    msg.what = POST;
                    msg.obj = resutl;
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }


    private  String post(String url, String json) throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GET://get请求
                    tv_result.setText(msg.obj.toString());
                    break;
                case POST://post请求
                    tv_result.setText(msg.obj.toString());
                    break;
            }
        }
    };




}
