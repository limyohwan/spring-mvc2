package hello.typeconverter.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyNumberFormatterTest {

    MyNumberFormatter formatter = new MyNumberFormatter();
    
    @Test
    void parse() throws ParseException{
        Number parse = formatter.parse("1,000", Locale.KOREA);
        Assertions.assertThat(parse).isEqualTo(1000L);
    }

    @Test
    void print(){
        String print = formatter.print(1000,Locale.KOREA);
        Assertions.assertThat(print).isEqualTo("1,000");
    }
}
