package hello.itemservice.validation;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

public class MessageCodesResolverTest {
    
    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void messageCodesResolverObject(){
        String[] resolveMessageCodes = codesResolver.resolveMessageCodes("required", "item");

        for(String s : resolveMessageCodes){
            System.out.println("messagecode = " + s);
        }

        assertThat(resolveMessageCodes).containsExactly("required.item", "required");
    }

    @Test
    void messageCodesResolverField(){
        String[] resolveMessageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);

        for(String s : resolveMessageCodes){
            System.out.println("messagecode = " + s);
        }

        assertThat(resolveMessageCodes).containsAnyOf("required");
    }
}
