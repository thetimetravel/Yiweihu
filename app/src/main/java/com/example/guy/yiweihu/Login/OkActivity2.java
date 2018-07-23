package com.example.guy.yiweihu.Login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.guy.yiweihu.R;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkActivity2 extends AppCompatActivity implements View.OnClickListener{

//    private Handler handler;
     private  OkHttpClient client=new  OkHttpClient();
    private static final int GET = 1;
    private Button b1;
    private  ProgressBar bar;
    /**
     * post请求
     */


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login2);
         b1 = (Button) findViewById(R.id.button1);
        EditText editText=(EditText)findViewById(R.id.editView);
        //设置点击事件
        b1.setOnClickListener(this);
        bar=(ProgressBar)findViewById(R.id.progress);





    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:
                bar.setVisibility(ProgressBar.VISIBLE);
                getDataFromByGet();
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



    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GET://get请求
                    bar.setVisibility(ProgressBar.GONE);
                    b1.setText(msg.obj.toString());
                    break;

            }

        }
    };




}
