package cn.cfanr.girlscode.ui.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.OnClick;
import cn.cfanr.girlscode.R;
import cn.cfanr.girlscode.app.RetrofitManager;
import cn.cfanr.girlscode.core.BaseBarActivity;
import cn.cfanr.girlscode.utils.ImageLoader;
import cn.cfanr.girlscode.utils.ToastUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class PhotoActivity extends BaseBarActivity {
    @BindView(R.id.iv_photo_girl)
    ImageView ivPhoto;

    private String imgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_photo;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        imgUrl = getIntent().getExtras().getString("imgUrl");
        String date = getIntent().getExtras().getString("date");
        ImageLoader.with(imgUrl, ivPhoto);
        this.setTitle(date);
    }

    @OnClick(R.id.fab_photo)
    void download(){
        RetrofitManager.builder().downloadImage(imgUrl)
                .subscribeOn(Schedulers.newThread())//在新线程中实现该方法
                .map(new Function<ResponseBody, Bitmap>() {

                    @Override
                    public Bitmap apply(ResponseBody responseBody) throws Exception {
                        String name = System.currentTimeMillis() + ".png";
                        if(writeResponseBodyToDisk(responseBody, name)) {//保存图片成功
                            Bitmap bitmap = BitmapFactory.decodeFile(getExternalFilesDir(null) + File.separator
                                    + name);
                            ToastUtil.show(PhotoActivity.this, "图片保存成功！");
                            return bitmap;//返回一个bitmap对象
                        }
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//在Android主线程中展示
                .subscribe(new Observer<Bitmap>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        ivPhoto.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(Throwable arg0) {

                    }

                    @Override
                    public void onComplete() {

                    }

                });
    }

    private boolean writeResponseBodyToDisk(ResponseBody body,String saveName) {
        try {
            // todo change the file location/name according to your needs
            File imgFile = new File(getExternalFilesDir(null) + File.separator + saveName);

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(imgFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.w("saveFile", "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }
}
