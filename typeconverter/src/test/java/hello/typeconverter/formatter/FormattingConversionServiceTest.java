package hello.typeconverter.formatter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

import hello.typeconverter.converter.IntegerToStringConverter;
import hello.typeconverter.converter.StringToIntegerConverter;

public class FormattingConversionServiceTest {
    
    @Test
    void formattingConversionServiceTest(){
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();

        conversionService.addConverter(new StringToIntegerConverter());
        conversionService.addConverter(new IntegerToStringConverter());

        conversionService.addFormatter(new MyNumberFormatter());

        String str = conversionService.convert(111, String.class);
        Assertions.assertThat(str).isEqualTo("111");
    }
}
