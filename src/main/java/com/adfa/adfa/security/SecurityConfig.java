package com.adfa.adfa.security;

import com.adfa.adfa.service.JwtService;
import com.adfa.adfa.web.exceptions.JwtAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {
    private final SecurityTokenFilter filter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public SecurityConfig(SecurityTokenFilter filter, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.filter = filter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }


    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration configuration) {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        return http
				.cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(ex -> {
                    ex.authenticationEntryPoint(jwtAuthenticationEntryPoint);
                })
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/auth/**").permitAll();
                    auth.requestMatchers("/auth/me").authenticated();
                    auth.requestMatchers(
                            HttpMethod.POST,
                            "/stadiums/**", "/teams/**", "/referees/**", "/matches/**"
                    ).authenticated();
                    auth.requestMatchers(
                            HttpMethod.PUT,
                            "/stadiums/**", "/teams/**", "/referees/**", "/matches/**"
                    ).authenticated();
                    auth.requestMatchers(
                            HttpMethod.DELETE,
                            "/stadiums/**", "/teams/**", "/referees/**", "/matches/**"
                    ).authenticated();
                    auth.anyRequest().permitAll();
                })
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

	@Bean
	public CorsConfigurationSource corsConfigurationSource () {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("http://localhost:5173");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		corsConfiguration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource config = new UrlBasedCorsConfigurationSource();
		config.registerCorsConfiguration("/**", corsConfiguration);
		return config;
	}
}
