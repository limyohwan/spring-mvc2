package hello.exception.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hello.exception.exception.BadRequestException;
import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ApiExceptionV2Controller {
    @GetMapping("/api2/members/{id}")
    public MemberDto getMember(@PathVariable String id){
        if (id.equals("ex")){
            throw new RuntimeException("잘못된 사용자입니다");
        }

        if (id.equals("bad")){
            throw new IllegalArgumentException("잘못된 입력값");
        }

        if (id.equals("user-ex")){
            throw new UserException("사용자 오류 ");
        }
        return new MemberDto(id, "hello" + id);
    }

    @GetMapping("/api2/response-status-ex1")
    public String responseStatuxEx1(){
        throw new BadRequestException();
    }

    @GetMapping("/api2/response-status-ex2")
    public String responseStatuxEx2(){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error.bad", new IllegalArgumentException());
    }

    @GetMapping("/api2/default-handler-ex")
    public String defaultHanderEx(@RequestParam Integer data){
        return "ok";
    }

    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String memberId;
        private String name;
    }
}
