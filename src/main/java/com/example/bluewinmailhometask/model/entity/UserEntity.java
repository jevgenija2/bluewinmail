package com.example.bluewinmailhometask.model.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "Users")
public class UserEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;
        private String name;
        private String surname;
        private String emailAddress;
        private double subscriptionMonthlyPayment;
        private String subscriptionPaymentCurrency;
        private String emailQuota; //capacity of mail box
        @CreationTimestamp
        private Date userCreated;

        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getSurname() {
                return surname;
        }

        public void setSurname(String surname) {
                this.surname = surname;
        }

        public String getEmailAddress() {
                return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
                this.emailAddress = emailAddress;
        }

        public double getSubscriptionMonthlyPayment() {
                return subscriptionMonthlyPayment;
        }

        public void setSubscriptionMonthlyPayment(double subscriptionMonthlyPayment) {
                this.subscriptionMonthlyPayment = subscriptionMonthlyPayment;
        }

        public String getSubscriptionPaymentCurrency() {
                return subscriptionPaymentCurrency;
        }

        public void setSubscriptionPaymentCurrency(String subscriptionPaymentCurrency) {
                this.subscriptionPaymentCurrency = subscriptionPaymentCurrency;
        }

        public String getEmailQuota() {
                return emailQuota;
        }

        public void setEmailQuota(String emailQuota) {
                this.emailQuota = emailQuota;
        }

        public Date getUserCreated(){
                return userCreated;
        }

        public void setUserCreated(Date userCreated){
                this.userCreated = userCreated;
        }

}
