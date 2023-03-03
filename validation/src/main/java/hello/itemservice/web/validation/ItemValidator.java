package hello.itemservice.web.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import hello.itemservice.domain.item.Item;

@Component
public class ItemValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors bindingResult) {
        Item item = (Item) target;

        if(!StringUtils.hasText(item.getItemName())){
            bindingResult.rejectValue("itemName", "required");
        }
        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000){
            bindingResult.rejectValue("price", "range", new Object[]{1000,1000000},null);
        }
        if(item.getQuantity() == null || item.getQuantity() > 9999){
            bindingResult.rejectValue("quantity", "max", new Object[]{9999},null);
        }
        if(item.getPrice() != null && item.getQuantity() !=null && item.getPrice() * item.getQuantity() <10000){
            bindingResult.reject("totalPriceMin", new Object[]{10000, item.getPrice() * item.getQuantity()},null);
        }
    }
    
}
