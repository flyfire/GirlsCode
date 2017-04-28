package cn.cfanr.girlscode.ui.binder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cfanr.girlscode.R;
import cn.cfanr.girlscode.model.Gank;
import cn.cfanr.girlscode.ui.activities.GankDetailActivity;
import cn.cfanr.girlscode.utils.DateUtil;
import me.drakeet.multitype.ItemViewProvider;

/**
 * @author xifan
 * @since 2017/3/28.
 */

public class GankViewBinder2 extends ItemViewProvider<Gank, GankViewBinder2.ViewHolder> {
    private Context mContext;

    public GankViewBinder2(Context context){
        this.mContext = context;
    }

    @NonNull
    @Override
    protected GankViewBinder2.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater,
                                                            @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_gank2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final GankViewBinder2.ViewHolder holder, @NonNull final Gank gank) {
        holder.tvTitle.setText(gank.desc);
        holder.tvAuthor.setText("via " + gank.who);
        holder.tvDate.setText(DateUtil.getYMDFormatDate(gank.publishedAt));
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

        @BindView(R.id.tv_gank2_title)
        TextView tvTitle;
        @BindView(R.id.tv_gank2_author)
        TextView tvAuthor;
        @BindView(R.id.tv_gank2_date)
        TextView tvDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
