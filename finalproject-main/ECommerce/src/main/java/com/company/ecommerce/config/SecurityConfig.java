package com.company.ecommerce.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    private final LogoutHandler logoutHandler;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()

                //for working without security uncomment BELOW ROWS
                .requestMatchers("/api/v1/auth/**" ,
                        "/customer/**",
                        "/products/**",
                        "/bucket/**", "/categories/**" , "/brands/**","/order/**", "/wishlist/**")
                .permitAll()


                //FROM HERE (comment for working without security)
//                .requestMatchers("/api/v1/auth/**" ,
//                        "/products", "/products/{id}",
//                        "/products/category/{categoryId}",
//                        "/products/searchByDescription",
//                        "/categories", "/categories/{id}",
//                        "/products/brand/{brandId}",
//                        "/brands" , "/brands/{id}",
//                        "/order/{orderId}","/customer/get-by-token/{token}")
//                    .permitAll()
//                .requestMatchers("/products","/products/delete-product/{id}", "/products/save-product", "/products/update-product/{id}",
//                        "/brands/delete-brand/{id}", "/brands/update-brand/{id}", "/brands/save-brand",
//                        "/categories/delete-category/{id}", "/categories/update-category/{id}", "/categories/save-category",
//                        "/order/update-status/{orderId}", "/order")
//                    .hasRole("ADMIN")
//                .requestMatchers("/bucket/add-to-bucket/{customer}/{perProduct}/{size}/{count}",
//                        "/bucket/delete-product-from-bucket/{customerId}/{perProductId}/{sizeId}",
//                        "/bucket/increment-product-number/{customer}/{perProduct}/{size}",
//                        "/bucket/decrement-product-number/{customer}/{perProduct}/{size}",
//                        "/bucket/purchase/{customerId}",
//                        "/wishlist/add-to-wishlist/{customerId}/{perProductId}")
//                    .hasRole("USER")
                //TO HERE

                .anyRequest()
                    .authenticated()
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());

        return http.build();
    }
}
