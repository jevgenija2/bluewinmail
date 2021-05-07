package com.example.bluewinmailhometask.testBeans;

import com.example.bluewinmailhometask.model.entity.UserEntity;
import com.example.bluewinmailhometask.service.DataBaseOperationsService;
import com.example.bluewinmailhometask.service.UserService;
import org.springframework.context.annotation.Import;

@Import({
        DataBaseOperationsService.class,
        UserEntity.class,
        UserService.class
})
public class BlueMailTestBeans {
}
