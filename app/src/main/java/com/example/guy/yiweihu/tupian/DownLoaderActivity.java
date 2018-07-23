package com.example.guy.yiweihu.tupian;


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.guy.yiweihu.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownLoaderActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String params="http://upload.wikimedia.org/wikipedia/commons/thumb/e/ea/Hukou_Waterfall.jpg/800px-Hukou_Waterfall.jpg";


    private Button btnFirst, btnSecond;
    private ProgressBar progress;
    private FrameLayout frameLayout;
    private Bitmap bitmap = null;
    ProgressDialog dialog = null;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tupian);
        btnFirst = (Button) this.findViewById(R.id.btnFirst);
        btnSecond = (Button) this.findViewById(R.id.btnSecond);
        progress = (ProgressBar) this.findViewById(R.id.progress);
        progress.setVisibility(View.GONE);
        imageView=(ImageView)findViewById(R.id.image1);
        frameLayout = (FrameLayout) this.findViewById(R.id.frameLayout);

        btnFirst.setOnClickListener(this);
        btnSecond.setOnClickListener(this);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
//关闭
                    ImageView view = (ImageView) frameLayout.findViewById(R.id.image);
                    view.setImageBitmap(bitmap);
                    dialog.dismiss();
                    break;
            }
        }
    };


    //前台ui线程在显示ProgressDialog，
//后台线程在下载数据，数据下载完毕，关闭进度框
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnFirst:
                dialog = ProgressDialog.show(this, "",
                        "下载数据，请稍等 …", true, true);
//启动一个后台线程
                new Thread(){
                    @Override
                    public void run() {
//这里下载数据
                        try {
                            URL url = new URL(params);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setDoInput(true);
                            conn.setRequestMethod("GET");
                            conn.setConnectTimeout(1000);
                            conn.connect();
                            InputStream inputStream = conn.getInputStream();
                            bitmap = BitmapFactory.decodeStream(inputStream);
                            Message msg = new Message();
                            msg.what = 1;
                            handler.sendMessage(msg);



                        } catch (MalformedURLException e1) {
                            e1.printStackTrace();
                        } catch (IOException e) {
// TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;
        }
    }
}


