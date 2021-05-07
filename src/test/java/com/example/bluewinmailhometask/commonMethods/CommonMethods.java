package com.example.bluewinmailhometask.commonMethods;

import com.example.bluewinmailhometask.model.entity.UserEntity;
import com.example.bluewinmailhometask.model.entity.UsersEntityModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CommonMethods {

    public static UsersEntityModel getJsonMockData() throws FileNotFoundException {
        Reader reader = new FileReader("src/test/resources/UserListMock.json");
        JsonElement elem = new JsonParser().parse(reader);
        Gson gson  = new GsonBuilder().create();
        Type userListType = new TypeToken<ArrayList<UserEntity>>(){}.getType();
        List<UserEntity> o = gson.fromJson(elem, userListType);
        UsersEntityModel usersEntityModel = new UsersEntityModel();
        usersEntityModel.setUsers(o);
        return usersEntityModel;

    }
}
