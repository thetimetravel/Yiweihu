package com.example.guy.yiweihu.http;

import android.database.Observable;

import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface HttpService {

    //上传图片和描述
    @Multipart
    @POST("上传的地址")
    rx.Observable uploadUserFile(
            @Part("fileName") RequestBody description,
            @Part("file\"; filename=\"image.png\"")RequestBody img
    );

    //上传图片和图片描述，图片类型，用户id等其它信息
    //如果除了图片外还需要传递其它信息，只需要增加几个@Part就可以了。
    @Multipart
    @POST("上传的地址")
    Observable<String> uploadUserFileAndId(
            @Part("describe") String describe,
            @Part("type") String type,
            @Part("userid") String userid,
            @Part("fileName") RequestBody description,
            @Part("file\"; filename=\"image.png\"")RequestBody img
    );
}
