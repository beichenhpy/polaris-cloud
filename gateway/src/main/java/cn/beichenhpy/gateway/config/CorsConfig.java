package cn.beichenhpy.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * @author beichenhpy
 * @version 0.0.1
 * @apiNote CorsConfig description：
 * @since 2021/5/9 9:45 下午
 */
@Configuration
public class CorsConfig {
    private static final String ALLOWED_HEADERS = "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN,token,username,client";
    private static final String ALLOWED_METHODS = "*";
    private static final String ALLOWED_ORIGIN = "*";
    private static final String ALLOWED_EXPOSE = "*";
    private static final Long MAX_AGE = 18000L;

    @Bean
    public CorsWebFilter corsWebFilter(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader(ALLOWED_HEADERS);
        corsConfiguration.addAllowedMethod(ALLOWED_METHODS);
        corsConfiguration.addAllowedOrigin(ALLOWED_ORIGIN);
        corsConfiguration.addExposedHeader(ALLOWED_EXPOSE);
        corsConfiguration.setMaxAge(MAX_AGE);
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        configurationSource.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsWebFilter(configurationSource);
    }

}
