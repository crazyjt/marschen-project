package com.example.marstest.UserLocation;

import java.util.List;

/**
 * Created by 钧童 on 2017/11/13.
 */

public class Location {
    private String status;
    //Result是自定义包含两个数据的类
    private List<Result> results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Location{" +
                "status='" + status + '\'' +
                ", results=" + results +
                '}';
    }
}
