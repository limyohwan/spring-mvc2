package hello.itemservice.web.validation;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.itemservice.web.validation.form.ItemSaveForm;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {
    
    @PostMapping("/add")
    public Object addItem(@RequestBody @Validated ItemSaveForm form, BindingResult bindingResult){
        log.info("API call");

        if(bindingResult.hasErrors()){
            log.info("hasErrors = {}", bindingResult);
            return bindingResult.getAllErrors();
        }

        log.info("success");
        return form;
    }
}
