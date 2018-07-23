package com.example.guy.yiweihu.recycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guy.yiweihu.R;


import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity  implements View.OnClickListener{

    private Button btn_add;
    private Button btn_delete;
    private Button btn_list;
    private Button btn_grid;
    private Button btn_flow;
    private RecyclerView recyclerview;
    private TextView tv_title;

    private ArrayList<String> datas;
    private MyAdapter adapter;
    int[] Icons={R.drawable.tb1,R.drawable.tb2,R.drawable.tb3,R.drawable.tb4};
    String[] Data={"通知","保障","临时任务","巡更"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

        initData();

        //设置RecyclerView的适配器
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);

        adapter = new MyAdapter(RecyclerActivity.this,datas,Icons,Data);
        recyclerview.setAdapter(adapter);

        //LayoutManager
        recyclerview.setLayoutManager(new LinearLayoutManager(RecyclerActivity.this, LinearLayoutManager.VERTICAL, false));
//        recyclerview.setLayoutManager(new GridLayoutManager(RecyclerActivity.this, 2, GridLayoutManager.VERTICAL, false));
        recyclerview.scrollToPosition(datas.size()-1);

        //添加RecyclerView的分割线
        recyclerview.addItemDecoration(new DividerListItemDecoration(RecyclerActivity.this,DividerListItemDecoration.VERTICAL_LIST));

        //设置点击某条的监听
        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                Toast.makeText(RecyclerActivity.this, "data=="+data, Toast.LENGTH_SHORT).show();
            }
        });
        //设置动画
        recyclerview.setItemAnimator(new DefaultItemAnimator());

    }
    private void initData() {
        datas = new ArrayList<>();
        //准备数据集合
        for (int i=0;i<4;i++){
            datas.add(Data[i]);
        }

    }


    private void initView() {
        setContentView(R.layout.activity_recyclerview);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_list = (Button) findViewById(R.id.btn_list);
        btn_grid = (Button) findViewById(R.id.btn_grid);
        btn_flow = (Button) findViewById(R.id.btn_flow);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("RecyclerView");

        //设置点击事件
        btn_add.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_list.setOnClickListener(this);
        btn_grid.setOnClickListener(this);
        btn_flow.setOnClickListener(this);
    }



    public void onItemClick(View view, int img) {
        //创建一个intent，指明跳转目标类
        Intent intent = new Intent(this, ImageDetail.class);
        //拿到数据传给intent
        intent.putExtra("image", img);
        //启动Activity
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                adapter.addData(0,"New_Content");
                recyclerview.scrollToPosition(0);
                break;
            case R.id.btn_delete:
                adapter.removeData(0);
                break;
            case R.id.btn_list:
                //设置List类型效果
                recyclerview.setLayoutManager(new LinearLayoutManager(RecyclerActivity.this,LinearLayoutManager.VERTICAL,false));
                break;
            case R.id.btn_grid:
                //设置Grid类型效果
                recyclerview.setLayoutManager(new GridLayoutManager(RecyclerActivity.this, 2, GridLayoutManager.VERTICAL, false));
//                recyclerview.scrollToPosition(99);
                break;
            case R.id.btn_flow:
                //设置瀑布流类型效果
                recyclerview.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                break;
        }
    }
}