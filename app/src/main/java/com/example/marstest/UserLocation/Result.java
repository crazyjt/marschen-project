package com.example.marstest.UserLocation;

/**
 * Created by 钧童 on 2017/11/13.
 */

public class Result {
    private String[] types;
    private String formatted_address;

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }
}
