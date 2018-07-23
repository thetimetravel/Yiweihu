package com.example.guy.yiweihu.recycler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guy.yiweihu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView适配器
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>   {

    private final Context context;
    private ArrayList<String> datas;
    private int[] mIcons;
    private String[] mData;




    public MyAdapter(Context context, ArrayList<String> datas,int[] mIcons,String[] mData) {
        this.context = context;
        this.datas = datas;
        this.mIcons=mIcons;
        this.mData=mData;
    }



    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //根据位置得到对应的数据
        String data = datas.get(position);
        holder.tv_title.setText(data);
        holder.iv_icon.setImageResource(mIcons[position]);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemView = View.inflate(context, R.layout.item_recyclerview, null);
        ViewHolder vh=new ViewHolder(itemView);
//        ImageView image=(ImageView)itemView.findViewById(R.id.iv_icon);
        vh.iv_icon=(ImageView)itemView.findViewById(R.id.iv_icon);
        Bitmap bitmap= BitmapFactory.decodeResource(context.getResources(),mIcons[2]);
//        image.setImageBitmap(bitmap);
        vh.iv_icon.setImageBitmap(bitmap);


        return new ViewHolder(itemView);
    }


    public void addData(int position, String data) {
        datas.add(position, data);


        //刷新适配器
        notifyItemInserted(position);
    }

    /**
     * 移除数据
     *
     * @param position
     */
    public void removeData(int position) {
        datas.remove(position);
        //刷新适配器
        notifyItemRemoved(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_icon;
        private TextView tv_title;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);




            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Toast.makeText(context, "dadsta=="+datas.get(getLayoutPosition()), Toast.LENGTH_SHORT).show();
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v, datas.get(getLayoutPosition()));
                    }
                }
            });

            iv_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "我是图片==" + getLayoutPosition(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * 点击RecyclerView某条的监听
     */
    public interface OnItemClickListener {

        /**
         * 当RecyclerView某个被点击的时候回调
         *
         * @param view 点击item的视图
         * @param data 点击得到的数据
         */
        public void onItemClick(View view, String data);

    }

    private OnItemClickListener onItemClickListener;

    /**
     * 设置RecyclerView某个的监听
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}