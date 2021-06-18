package edu.reactive.server.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Configuration
@EnableWebFlux
class WebFluxConfig implements WebFluxConfigurer {

    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        configurer.defaultCodecs().enableLoggingRequestDetails(true);
    }

    public static class LocalDateSerializer extends JsonSerializer<LocalDate> {
        @Override
        public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value != null) {
                gen.writeString(value.toString());
            } else {
                gen.writeNull();
            }
        }
    }

    public static class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value != null) {
                gen.writeString(value.toString());
            } else {
                gen.writeNull();
            }
        }
    }

    public static class OffsetDateTimeSerializer extends JsonSerializer<OffsetDateTime> {
        @Override
        public void serialize(OffsetDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value != null) {
                gen.writeString(value.toString());
            } else {
                gen.writeNull();
            }
        }
    }


    public static class CustomTimeModule extends SimpleModule {
        private static final long serialVersionUID = 1L;

        public CustomTimeModule() {
            super(new Version(1, 0, 0, "RELEASE", WebFluxConfig.class.getPackageName(), CustomTimeModule.class.getName()));

            addSerializer(LocalDate.class, new LocalDateSerializer());
            addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
            addSerializer(OffsetDateTime.class, new OffsetDateTimeSerializer());
        }
    }

    @Bean
    Jackson2JsonDecoder jackson2JsonDecoder(){
        ObjectMapper requestObjectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
        return new Jackson2JsonDecoder(requestObjectMapper);
    }

    @Bean
    Jackson2JsonEncoder jackson2JsonEncoder(){
        ObjectMapper responseObjectMapper = new ObjectMapper().registerModule(new CustomTimeModule())
                .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return new Jackson2JsonEncoder(responseObjectMapper);
    }

    @Bean
    WebFluxConfigurer webFluxConfigurer(){
        return new WebFluxConfigurer() {
            @Override
            public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
                configurer.defaultCodecs().jackson2JsonDecoder(jackson2JsonDecoder());
                configurer.defaultCodecs().jackson2JsonEncoder(jackson2JsonEncoder());
            }
        };
    }
}
