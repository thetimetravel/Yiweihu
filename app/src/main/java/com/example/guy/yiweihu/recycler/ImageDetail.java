package com.example.guy.yiweihu.recycler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.guy.yiweihu.R;

/**
 * Date:2017/3/15
 * Author:李祥
 * Function:
 */
public class ImageDetail extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagedetail);
        Intent intent = getIntent();
        int image = intent.getIntExtra("image", R.mipmap.ic_launcher);
        ImageView imag = (ImageView) findViewById(R.id.details_img);
        imag.setImageResource(image);


    }
}
