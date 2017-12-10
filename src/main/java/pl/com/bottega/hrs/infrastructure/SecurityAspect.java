package pl.com.bottega.hrs.infrastructure;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import pl.com.bottega.hrs.application.users.CurrentUser;
import pl.com.bottega.hrs.application.users.SecurityException;

@Component
@Aspect
public class SecurityAspect {

    private CurrentUser currentUser;

    public SecurityAspect(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @Before("@within(pl.com.bottega.hrs.infrastructure.Secured) || " +
            "@annotation(pl.com.bottega.hrs.infrastructure.Secured)")
    public void checkSecurity() {
        if(!currentUser.isAuthenticated())
            throw new SecurityException();
    }

}
