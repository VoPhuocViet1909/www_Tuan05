package iuh.fit.vophuocviet_22730761_springboot_shopping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // create sample in-memory users
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("123"))
                .roles("ADMIN")
                .build();

        UserDetails customer = User.withUsername("customer")
                .password(passwordEncoder().encode("111"))
                .roles("CUSTOMER")
                .build();

        return new InMemoryUserDetailsManager(admin, customer);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // public resources
                        .requestMatchers("/login", "/css/**", "/js/**", "/images/**", "/test").permitAll()

                        // ADMIN-only - đặt trước để match ưu tiên
                        .requestMatchers("/product/add", "/product/edit/**", "/product/update/**", "/product/delete/**").hasRole("ADMIN")
                        // nếu muốn chặt hơn: bảo vệ POST edit/add
                        .requestMatchers(HttpMethod.POST, "/product/add", "/product/edit/**").hasRole("ADMIN")

                        // accessible to both CUSTOMER and ADMIN (viewing)
                        .requestMatchers("/product", "/product/*", "/product/detail/**", "/home", "/").hasAnyRole("CUSTOMER", "ADMIN")

                        // all other requests require authentication
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/product", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        return http.build();
    }
}