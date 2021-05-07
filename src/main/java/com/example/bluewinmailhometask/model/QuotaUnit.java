package com.example.bluewinmailhometask.model;

import com.example.bluewinmailhometask.exception.CustomResourceNotFoundException;

import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;

//Enum that holds all available quota options
public enum QuotaUnit {
    UP_TO_1GB("1","Up to 1 GB"),
    UP_TO_10GB("10", "Up to 10 GB"),
    UP_TO_20GB("20", "Up to 20 GB"),
    UNLIMITED("unlimited", "Unlimited");

    private final String key;
    private final String value;

    QuotaUnit(String key ,String value){
        this.key = key;
        this.value = value;
    }

    public String getKey(){return key;}
    public String getValue(){
        return value;
    }

    public static String getCapacityByKey(String key){
        for(QuotaUnit quota: QuotaUnit.values()){
            if(equalsIgnoreCase(quota.key, key)){
                return quota.getValue();
            }
        }
        throw new CustomResourceNotFoundException("Email qouta with provided key:" + key +" not available");
    }
}
