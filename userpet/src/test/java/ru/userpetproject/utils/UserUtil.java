package ru.userpetproject.utils;

import lombok.experimental.UtilityClass;
import ru.userpetproject.entity.Email;
import ru.userpetproject.entity.Phone;
import ru.userpetproject.entity.User;
import ru.userpetproject.enums.UserStatus;

@UtilityClass
public class UserUtil {

    public static User getStubUser(String userName, String phoneNumber, String phoneType, String userEmail) {
        User user = new User();
        user.setName(userName);
        user.setStatus(UserStatus.CREATED);

        Phone phone = new Phone();
        phone.setNumber(phoneNumber);
        phone.setType(phoneType);
        user.setPhone(phone);

        Email email = new Email();
        email.setAddress(userEmail);
        user.setEmail(email);
        return user;
    }

    public static User getStubUser() {
        return getStubUser("John", "+79876546565", "work", "john@mail.ru");
    }
}
