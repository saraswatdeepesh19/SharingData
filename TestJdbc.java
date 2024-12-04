import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayLoggingFilter {

    private static final Logger logger = LoggerFactory.getLogger(GatewayLoggingFilter.class);

    @Bean
    public GlobalFilter logRoutedUrlFilter() {
        return (exchange, chain) -> {
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        // Extract the routed URL from the exchange attributes
                        var routedUrl = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
                        if (routedUrl != null) {
                            logger.info("Routed to URL: {}", routedUrl);
                        } else {
                            logger.warn("Routed URL not found in exchange attributes");
                        }
                    }));
        };
    }
}
