package com.example.guy.yiweihu.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guy.yiweihu.MainActivity;
import com.example.guy.yiweihu.R;
import com.example.guy.yiweihu.recycler.RecyclerActivity;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.widget.Toast.*;

public class LoginActivity extends AppCompatActivity{

    private EditText tv_userName;
    private EditText tv_paassword;
    private Button btn_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        tv_userName=(EditText)findViewById(R.id.tv_userName);
        tv_paassword=(EditText)findViewById(R.id.tv_password);
        btn_login=(Button)findViewById(R.id.btn_login);
//        loginfo();

    }


//    private void loginfo() {
//        OkHttpClient client=new  OkHttpClient();
//        Request request=new Request.Builder()
//                .url("http://www.baidu.com")
//                .build();
//        Call call=client.newCall(request);
//
//        try{
//            Response response=call.execute();
//            String string=response.body().string();
//            byte[] bytes=response.body().bytes();
//            InputStream is=response.body().byteStream();
//            call.enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                  System.out.println("你失败了");
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    Intent intent=new Intent(LoginActivity.this, RecyclerActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//            });
//
//        }catch(IOException e){
//            e.printStackTrace();
//
//        }
//
//    }



}
