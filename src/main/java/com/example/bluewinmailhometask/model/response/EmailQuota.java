package com.example.bluewinmailhometask.model.response;

import com.example.bluewinmailhometask.model.Subscription;
import lombok.Data;

@Data
public class EmailQuota {

    private Subscription subscription; //monthly payment: amount to be paid each month; + currency that is used.
    private String capacity; //mail box capacity unit (ex. 1GB, 10GB, UNLIMITED)
}
