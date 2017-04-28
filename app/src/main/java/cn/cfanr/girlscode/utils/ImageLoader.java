package cn.cfanr.girlscode.utils;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import cn.cfanr.girlscode.app.AppController;

/**
 * @author xifan
 * @since 2017/3/28.
 */

public class ImageLoader {

    public static void with(String url, ImageView imageView){
        Picasso.with(AppController.getInstance()).load(url).into(imageView);
    }
}
