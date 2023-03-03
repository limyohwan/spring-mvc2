package hello.typeconverter.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import hello.typeconverter.type.IpPort;

public class IpPortToStringConverter implements Converter<IpPort, String>
{

    @Override
    @Nullable
    public String convert(IpPort source) {
        // TODO Auto-generated method stub

        return source.getIp()+":" +source.getPort();
    }
    
}
