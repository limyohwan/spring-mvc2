package hello.login.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.PatternMatchUtils;

import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] whiteList = {"/", "/members/add","/login", "/logout", "/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try{
            log.info("auth check filter start {}", requestURI);

            if(isLoginCheckPath(requestURI)){
                log.info("auth check login start {}" , requestURI);
                HttpSession session = httpRequest.getSession(false);
                if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null){
                    log.info("no auth user request {}", requestURI);
                    httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
                    return;
                }
            }
            chain.doFilter(request, response);
        }catch(Exception e){
            throw e;
        }finally{
            log.info("auth check fitler close {}", requestURI);
        }

    }

    private boolean isLoginCheckPath(String requestURI){
        return !PatternMatchUtils.simpleMatch(whiteList, requestURI);
    }
    
}
