package cn.cfanr.girlscode.ui.binder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.cfanr.girlscode.R;
import cn.cfanr.girlscode.model.Gank;
import cn.cfanr.girlscode.ui.activities.GankDetailActivity;
import me.drakeet.multitype.ItemViewProvider;

/**
 * @author xifan
 * @since 2017/3/28.
 */

public class GankViewBinder extends ItemViewProvider<Gank, GankViewBinder.ViewHolder> {
    private Context mContext;

    public GankViewBinder(Context context){
        this.mContext = context;
    }

    @NonNull
    @Override
    protected GankViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater,
                                                           @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_gank, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final GankViewBinder.ViewHolder holder, @NonNull final Gank gank) {
        holder.tvGank.setText(gank.desc);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GankDetailActivity.class);
                intent.putExtra("webUrl", gank.url);
                mContext.startActivity(intent);
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvGank;

        public ViewHolder(View itemView) {
            super(itemView);
            tvGank = (TextView) itemView.findViewById(R.id.tv_gank_title);
        }

    }
}
