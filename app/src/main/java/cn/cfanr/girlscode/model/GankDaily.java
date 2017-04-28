package cn.cfanr.girlscode.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by xifan on 2017/1/23.
 */

public class GankDaily extends BaseModel{
    public List<String> category;
    public Result results;
    public String error;

    public class Result{
        @SerializedName("Android") public List<Gank> androidList;
        @SerializedName("休息视频") public List<Gank> restList;
        @SerializedName("iOS") public List<Gank> iOSList;
        @SerializedName("福利") public List<GirlGank> girlsList;
        @SerializedName("拓展资源") public List<Gank> sourceList;
        @SerializedName("瞎推荐") public List<Gank> recommendList;
        @SerializedName("App") public List<Gank> appList;
    }
}
