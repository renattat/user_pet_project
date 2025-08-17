package ru.userpetproject.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ru.userpetproject.entity.User;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(public ru.userpetproject.entity.User getUser(long))")
    public void beforeGetUserAdvice(JoinPoint jointPoint) {
        System.out.println("beforeGetUserAdvice: попытка получить пользователя c id = " + jointPoint.getArgs()[0]);
    }

    @Before("execution(public ru.userpetproject.entity.User ru.userpetproject.controller.UserController.addNewUser(ru.userpetproject.entity.User))")
    public void beforePostUserAdvice(JoinPoint jointPoint) {
        var createdUser = (User) jointPoint.getArgs()[0];
        System.out.println("beforePostUserAdvice: попытка создать пользователя : " +
                            "\nname: " +  createdUser.getName() +
                            "\nemail: " +  createdUser.getEmail());
    }
}
