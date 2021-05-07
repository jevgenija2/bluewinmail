package com.example.bluewinmailhometask.model.request;

import com.example.bluewinmailhometask.model.response.EmailQuota;
import lombok.Data;
import lombok.NonNull;

@Data
public class User {
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @NonNull
    private String emailAddress;
    @NonNull
    private EmailQuota emailQuota;
}
