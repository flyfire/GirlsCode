package cn.cfanr.girlscode.model;

import java.util.List;

/**
 * Created by xifan on 2017/1/23.
 */

public class GankIO extends BaseModel {
    public boolean error;
    public List<Gank> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Gank> getResults() {
        return results;
    }

    public void setResults(List<Gank> results) {
        this.results = results;
    }
}
