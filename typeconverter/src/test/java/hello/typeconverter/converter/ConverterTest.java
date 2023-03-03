package hello.typeconverter.converter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import hello.typeconverter.type.IpPort;

public class ConverterTest {

    @Test
    void stringTOInteger(){
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer result = converter.convert("10");
        Assertions.assertThat(result).isEqualTo(10);
    }

    @Test
    void integerToStringConverter(){
        IntegerToStringConverter converter = new IntegerToStringConverter();
        String result = converter.convert(10);
        Assertions.assertThat(result).isEqualTo("10");
    }
    
    @Test
    void stringToipPortConverter(){
        IpPortToStringConverter converter = new IpPortToStringConverter();
        IpPort source =new IpPort("1.1.1.1", 8080);
        String result = converter.convert(source);
        Assertions.assertThat(result).isEqualTo("1.1.1.1:8080");

    }

    @Test
    void ipPortToString(){
        StringToIpPortConverter converter = new StringToIpPortConverter();
        String source = "1.1.1.1:8080";
        IpPort result = converter.convert(source);
        Assertions.assertThat(result).isEqualTo(new IpPort("1.1.1.1", 8080));

    }
}
