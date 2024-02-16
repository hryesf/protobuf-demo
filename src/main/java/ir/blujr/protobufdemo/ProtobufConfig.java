package ir.blujr.protobufdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;

@Configuration
public class ProtobufConfig {

    @Bean
    public ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }

    /*@Bean
    public ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter(
                JsonFormat.parser(),
                JsonFormat.printer()
                        .includingDefaultValueFields()
                        .omittingInsignificantWhitespace()
                        .usingTypeRegistry(JsonFormat.TypeRegistry.getEmptyTypeRegistry())
        );
    }*/
}
