package com.example.guy.yiweihu.http;

import android.support.v7.app.AppCompatActivity;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;

public class UserinfoServer extends AppCompatActivity {
    //上传头像
    //参数是，图片路径，描述，和subscriber。
    public static void uploadUserIcon(String imgpath, String des, Subscriber<String> subscriber) {
        //构建文件描述的RequestBody
        RequestBody description =RequestBody.create(MediaType.parse("multipart/form-data"), des);
//根据图片路径构建图片的RequestBody
        File file = new File(imgpath);
        RequestBody imgbody = RequestBody.create(MediaType.parse("multipart/form-data"),file);

//获取HttpManager单例，并且调用doHttpRequest方法

//只要我们调用下面这个方法就会自动返回一个
//rxjava的Observable对象，然后这个Observable对象就会执行网络请求并返回给
//参数里的subscriber对象

        HttpManager.getInstance().getHttpService().uploadUserFile(description, imgbody);
        HttpManager.getInstance().doHttpRequest(HttpManager.getInstance().getHttpService().uploadUserFile(description, imgbody), subscriber);
    }

}
