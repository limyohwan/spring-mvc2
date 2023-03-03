package hello.login.web.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SessionInfoController {
    
    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null){
            return "세션이없음";
        }
        session.getAttributeNames().asIterator()
        .forEachRemaining(name -> log.info("session namae = {}, value = {}", name, session.getAttribute(name)));

        log.info("sessionId = {}", session.getId());
        log.info("getMaxInactiveInterval = {}", session.getMaxInactiveInterval());
        log.info("getCreationTime = {}", session.getCreationTime());
        log.info("getLastAccessedTime = {}", session.getLastAccessedTime());
        log.info("isNew = {}", session.isNew());

        return "session print";
    }
}
