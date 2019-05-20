package bupt.dawsonlee1790.sop.certification;

import bupt.dawsonlee1790.sop.components.User;
import bupt.dawsonlee1790.sop.exception.UnthorizationException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class ActorAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private User user;

    @Before("@annotation(actor)")
    public void validate(JoinPoint joinPoint, Actor actor) throws IOException, UnthorizationException {
        List<Role> roleList = Arrays.asList(actor.value());
        List<Role> roleList2 = user.getGroupList();
        boolean flag = roleList.containsAll(roleList2);
        boolean flag2 = roleList2.containsAll(roleList);
        if (!flag && !flag2) {
            throw new UnthorizationException();
        }
    }
}
