package com.example.bluewinmailhometask.service;

import com.example.bluewinmailhometask.exception.CustomResourceNotFoundException;
import com.example.bluewinmailhometask.model.QuotaUnit;
import com.example.bluewinmailhometask.model.entity.UserEntity;
import com.example.bluewinmailhometask.model.request.User;
import com.example.bluewinmailhometask.model.response.EmailQuota;
import com.example.bluewinmailhometask.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Repository
@RequiredArgsConstructor
public class DataBaseOperationsService {

    @Autowired
    UserRepository userRepository;

     public UserEntity createUser(User user){
         UserEntity userEntity = new UserEntity();
         userEntity.setEmailAddress(user.getEmailAddress());
         userEntity.setName(user.getName());
         userEntity.setSurname(user.getSurname());
         EmailQuota emailQuota = user.getEmailQuota();
         userEntity.setSubscriptionMonthlyPayment(emailQuota.getSubscription().getPayment());
         userEntity.setSubscriptionPaymentCurrency(emailQuota.getSubscription().getCurrency());
         userEntity.setEmailQuota(QuotaUnit.getCapacityByKey(user.getEmailQuota().getCapacity()));

         return userRepository.save(userEntity);
     }

     public UserEntity updateUser(UserEntity user){
         Optional<UserEntity> userToBeUpdated = userRepository.findById(user.getId());
         if(userToBeUpdated.isPresent()){
             UserEntity updatedUser = userToBeUpdated.get();
             updatedUser.setEmailQuota(QuotaUnit.getCapacityByKey(user.getEmailQuota()));
             updatedUser.setSubscriptionMonthlyPayment(user.getSubscriptionMonthlyPayment());
             updatedUser.setSubscriptionPaymentCurrency(user.getSubscriptionPaymentCurrency());
             userRepository.save(updatedUser);
            return updatedUser;
         } else throw new CustomResourceNotFoundException("User with " + user.getId()+" id not found.");
     }

    public boolean checkIfUserExists(@NonNull String emailAddress) {
         UserEntity userEntity = userRepository.findByEmailAddress(emailAddress);

         return nonNull(userEntity);
    }

     public List<UserEntity> getAllUsers(){
         return userRepository.findAll();
     }

     public UserEntity getUserById(long id){
         Optional<UserEntity> userEntity = userRepository.findById(id);
         if(userEntity.isPresent())
             return userEntity.get();
         else throw new CustomResourceNotFoundException("User with "+ id  +" id has not been found");
     }

     public void deleteUser(long id){
         Optional<UserEntity> userEntity = userRepository.findById(id);
         if(userEntity.isPresent())
             userRepository.delete(userEntity.get());
         else throw new CustomResourceNotFoundException("User with "+ id  +" id has not been found");
     }
}
