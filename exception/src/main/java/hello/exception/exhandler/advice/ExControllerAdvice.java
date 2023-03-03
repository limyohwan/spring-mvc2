package hello.exception.exhandler.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandler(IllegalArgumentException e){
        log.error("illegalExHandler = " , e);
        return new ErrorResult("Bad", e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHander(UserException e){
        log.error("userExHander = " , e);
        ErrorResult errorResult = new ErrorResult("user-ex", e.getMessage());
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST); 
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHander(Exception e){
        log.error("exHander = " , e);
        ErrorResult errorResult = new ErrorResult("ex", e.getMessage());
        return errorResult; 
    }
}
