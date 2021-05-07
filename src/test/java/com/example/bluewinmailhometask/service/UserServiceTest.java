package com.example.bluewinmailhometask.service;


import com.example.bluewinmailhometask.exception.CustomResourceNotFoundException;
import com.example.bluewinmailhometask.model.QuotaUnit;
import com.example.bluewinmailhometask.model.Subscription;
import com.example.bluewinmailhometask.model.entity.UserEntity;
import com.example.bluewinmailhometask.model.entity.UsersEntityModel;
import com.example.bluewinmailhometask.model.request.User;
import com.example.bluewinmailhometask.model.response.EmailQuota;
import com.example.bluewinmailhometask.repository.UserRepository;
import com.example.bluewinmailhometask.testBeans.BlueMailTestBeans;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;


import static com.example.bluewinmailhometask.commonMethods.CommonMethods.getJsonMockData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest(classes = BlueMailTestBeans.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    DataBaseOperationsService dataBaseOperationsService;
    @MockBean
    UserRepository userRepository;
    @MockBean
    RestTemplate restTemplate;

    @BeforeEach
    public void setupMockBehaiviour() throws Exception{
        when(dataBaseOperationsService.getAllUsers()).thenReturn(getJsonMockData().getUsers());
    }

    @Test
    public void testGetUserList() {
        UsersEntityModel userList = userService.getUserList();

        assertNotNull(userList);
        assertFalse(userList.getUsers().isEmpty());
        assertEquals(3, userList.getUsers().size());

    }

    @Test
    public void testUserCreate(){
        when(dataBaseOperationsService.createUser(any())).thenReturn(mockResponse());
        UserEntity userEntity = userService.createUser(mockUser());

        assertNotNull(userEntity);
        assertEquals("Andrew", userEntity.getName());
        assertEquals("Smith", userEntity.getSurname());
    }

    @Test
    public void testCreateUserEmailAlreadyExists() {
        when(dataBaseOperationsService.checkIfUserExists(anyString())).thenReturn(true);
        User user = mockUser();

        RuntimeException exception = assertThrows(CustomResourceNotFoundException.class, () -> userService.createUser(user));

        assertEquals("User with email address: " + user.getEmailAddress() + " already exists.", exception.getMessage());

    }

    public User mockUser(){
        Subscription subscription = new Subscription(1.00, "$");
        EmailQuota emailQuota = new EmailQuota();
        emailQuota.setSubscription(subscription);
        emailQuota.setCapacity("10");

        return new User("Andrew", "Smith", "andrew.smith@testMail.com", emailQuota);
    }

    private UserEntity mockResponse(){
        UserEntity userEntity = new UserEntity();
        userEntity.setName("Andrew");
        userEntity.setSurname("Smith");
        userEntity.setEmailAddress("andrew.smith@testMail.com");
        userEntity.setEmailQuota(QuotaUnit.getCapacityByKey("10"));
        userEntity.setSubscriptionPaymentCurrency("$");
        userEntity.setSubscriptionMonthlyPayment(1.00);

        return userEntity;

    }
}
