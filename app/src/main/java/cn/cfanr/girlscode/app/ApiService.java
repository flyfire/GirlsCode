package cn.cfanr.girlscode.app;


import cn.cfanr.girlscode.model.GankDaily;
import cn.cfanr.girlscode.model.GankIO;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * @author cfanr
 * @since 2017/1/23.
 */

public interface ApiService {
    /**
     * 每日数据
     * @param year
     * @param month
     * @param day
     * @return
     */
    @GET("day/{year}/{month}/{day}")
    Observable<GankDaily> getGankDaily(@Path("year") int year, @Path("month") int month, @Path("day") int day);

    /**
     * 分类数据
     * @param type 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * @param size 请求个数
     * @param page 页数
     * @return
     */
    @GET("data/{type}/{size}/{page}")
    Observable<GankIO> getGankData(@Path("type") String type, @Path("size")int size, @Path("page") int page);

    @GET
    Observable<ResponseBody> downloadImage(@Url String fileUrl);
}
