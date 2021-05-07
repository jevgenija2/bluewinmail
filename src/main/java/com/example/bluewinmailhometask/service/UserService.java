package com.example.bluewinmailhometask.service;

import com.example.bluewinmailhometask.exception.CustomResourceNotFoundException;
import com.example.bluewinmailhometask.model.entity.UserEntity;
import com.example.bluewinmailhometask.model.entity.UsersEntityModel;
import com.example.bluewinmailhometask.model.request.User;
import com.example.bluewinmailhometask.model.response.EmailQuota;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final DataBaseOperationsService dataBaseOperationsService;

    public UserEntity updateEmailQuota(long id, EmailQuota emailQuota){
        UserEntity userToBeUpdated = new UserEntity();
        userToBeUpdated.setId(id);
        userToBeUpdated.setEmailQuota(emailQuota.getCapacity());
        userToBeUpdated.setSubscriptionMonthlyPayment(emailQuota.getSubscription().getPayment());
        userToBeUpdated.setSubscriptionPaymentCurrency(emailQuota.getSubscription().getCurrency());

        return dataBaseOperationsService.updateUser(userToBeUpdated);
    }

    public UsersEntityModel getUserList(){
        UsersEntityModel usersEntityModel = new UsersEntityModel();
        usersEntityModel.setUsers(dataBaseOperationsService.getAllUsers());
        return usersEntityModel;
    }

    public UserEntity getUserById(long id){
        return dataBaseOperationsService.getUserById(id);
    }

    public UserEntity createUser(User user)  {
        if(!dataBaseOperationsService.checkIfUserExists(user.getEmailAddress()))
            return dataBaseOperationsService.createUser(user);
        else throw new CustomResourceNotFoundException("User with email address: " + user.getEmailAddress() + " already exists.");
    }
}
