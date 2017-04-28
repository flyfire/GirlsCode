package cn.cfanr.girlscode.ui.binder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cn.cfanr.girlscode.R;
import cn.cfanr.girlscode.model.Gank;
import cn.cfanr.girlscode.model.GirlGank;
import cn.cfanr.girlscode.ui.activities.PhotoActivity;
import cn.cfanr.girlscode.utils.DateUtil;
import cn.cfanr.girlscode.utils.ImageLoader;
import me.drakeet.multitype.ItemViewProvider;

/**
 * @author xifan
 * @since 2017/3/28.
 */

public class GirlViewBinder extends ItemViewProvider<GirlGank, GirlViewBinder.ViewHolder> {
    private Context mContext;

    public GirlViewBinder(Context context){
        this.mContext = context;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_daily_head, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, final @NonNull GirlGank gank) {
        holder.setData(gank);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PhotoActivity.class);
                intent.putExtra("imgUrl", gank.url);
                intent.putExtra("date", DateUtil.getYMDFormatDate(gank.publishedAt));
                mContext.startActivity(intent);
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivGirl;
        private TextView tvDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ivGirl = (ImageView) itemView.findViewById(R.id.iv_daily_head);
            tvDate = (TextView) itemView.findViewById(R.id.tv_daily_date);
        }

        private void setData(Gank gank){
            ImageLoader.with(gank.url, ivGirl);
            tvDate.setText(DateUtil.getYMDFormatDate(gank.publishedAt));
        }
    }
}
