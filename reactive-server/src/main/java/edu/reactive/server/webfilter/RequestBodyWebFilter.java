package edu.reactive.server.webfilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebExchangeDecorator;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Component
public class RequestBodyWebFilter implements WebFilter {
    private static final ObjectMapper OBJECT_MAPPER = makeMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestBodyWebFilter.class);

    private static ObjectMapper makeMapper() {
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        if(HttpMethod.GET.equals(exchange.getRequest().getMethod())) {
            return chain.filter(exchange);
        } else {
            return chain.filter(decorate(exchange));
        }
    }

    private ServerWebExchange decorate(final ServerWebExchange exchange) {
        final ServerHttpRequest decorated = new ServerHttpRequestDecorator(exchange.getRequest()) {
            @Override
            public Flux<DataBuffer> getBody() {
                return super.getBody()
                        .collectList()
                        .map(buffers -> {
                            String message = null;
                            try (ByteArrayOutputStream commonBuffer = new ByteArrayOutputStream()) {
                                for (DataBuffer buf : buffers) {
                                    Channels.newChannel(commonBuffer).write(buf.asByteBuffer().asReadOnlyBuffer());
                                }
                                message = commonBuffer.toString(StandardCharsets.UTF_8);
                                LOGGER.info("Controller received a request {}", new String(message.getBytes(StandardCharsets.UTF_8)));
                                var request = parseRequest(message);
                                validateFieldsAsArray(request);
                            } catch (IOException e) {
                                throw new WebFilterException("Byte buffer exception: ", e);
                            }
                            return buffers;
                        })
                        .flatMapMany(Flux::fromIterable);
            }
        };

        return new ServerWebExchangeDecorator(exchange) {
            @Override
            public ServerHttpRequest getRequest() {
                return decorated;
            }
        };
    }

    @SuppressWarnings("rawtypes")
    private Map parseRequest(final String message) throws JsonProcessingException {
        try {
            final Map request = OBJECT_MAPPER.readValue(message, Map.class);
            LOGGER.debug("Controller request was parsed as: {}", request);
            return request;
        } catch (JsonProcessingException e) {
            throw new WebFilterException("Invalid JSON format");
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void validateFieldsAsArray(final Map request) {
        if (request.isEmpty()) {
            throw new WebFilterException("Empty response body");
        }
    }
}
