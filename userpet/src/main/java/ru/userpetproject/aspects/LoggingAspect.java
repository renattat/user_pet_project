package ru.userpetproject.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.userpetproject.dto.user.UserReq;

@Aspect
@Component
public class LoggingAspect {

    static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(public * ru.userpetproject.controller.UserController.get(long))")
    public void beforeGetUserAdvice(JoinPoint jointPoint) {
        log.info("beforeGetUserAdvice: попытка получить пользователя c id = {}", jointPoint.getArgs()[0]);
    }

    @Before("execution(public * ru.userpetproject.controller.UserController.create(ru.userpetproject.dto.user.UserReq))")
    public void beforePostUserAdvice(JoinPoint jointPoint) {
        var userReq = (UserReq) jointPoint.getArgs()[0];
        log.info("beforePostUserAdvice: попытка создать пользователя : \nname: {} \nemails: {} \nphones: {}",
                userReq.getName(),
                userReq.getEmails(),
                userReq.getPhones());
    }
}
