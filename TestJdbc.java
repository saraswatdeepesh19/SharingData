import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayLoggingFilter {

    private static final Logger logger = LoggerFactory.getLogger(GatewayLoggingFilter.class);

    @Bean
    public GlobalFilter logRoutedUrlFilter() {
        return (exchange, chain) -> {
            // Log the original request URL and the routed URL
            exchange.getAttributes().computeIfPresent(
                ServerWebExchange.GATEWAY_REQUEST_URL_ATTR,
                (key, value) -> {
                    logger.info("Routed to URL: {}", value);
                    return value;
                });

            // Continue with the filter chain
            return chain.filter(exchange);
        };
    }
}
