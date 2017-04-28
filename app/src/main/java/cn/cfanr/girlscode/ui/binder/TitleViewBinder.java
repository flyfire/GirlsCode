package cn.cfanr.girlscode.ui.binder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.cfanr.girlscode.R;
import cn.cfanr.girlscode.model.Title;
import me.drakeet.multitype.ItemViewProvider;

/**
 * @author xifan
 * @since 2017/3/28.
 * @version
 */

public class TitleViewBinder extends ItemViewProvider<Title, TitleViewBinder.ViewHolder>{
    @NonNull
    @Override
    protected TitleViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater,
                                                            @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull TitleViewBinder.ViewHolder holder, @NonNull Title title) {
        holder.setData(title);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_type_title);
        }

        public void setData(Title title){
            tvTitle.setText(title.title);
        }
    }
}
