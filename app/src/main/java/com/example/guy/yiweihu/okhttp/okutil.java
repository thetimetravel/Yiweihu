package com.example.guy.yiweihu.okhttp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.guy.yiweihu.R;
import com.zhy.http.okhttp.OkHttpUtils;

public class okutil extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ok_util);
    }
//
//    public void getDataByOkhttputils()
//    {
//        String url = "http://www.zhiyun-tech.com/App/Rider-M/changelog-zh.txt";
//        url="http://api.m.mtime.cn/PageSubArea/TrailerList.api";
//        OkHttpUtils
//                .get()
//                .url(url)
//                .id(100)
//                .build()
//                .execute(new MyStringCallback());
//    }
//
//    public void getDataByOkhttputils()
//    {
//        String url = "http://www.zhiyun-tech.com/App/Rider-M/changelog-zh.txt";
//        url="http://api.m.mtime.cn/PageSubArea/TrailerList.api";
//        OkHttpUtils
//                .post()
//                .url(url)
//                .id(100)
//                .build()
//                .execute(new MyStringCallback());
//    }


}
