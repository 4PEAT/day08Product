package sdacademy.day08product.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration // Marks this class as a Spring configuration class
public class SecurityConfig {

    // Configures the HTTP security for the application
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Allow everyone to access the home page
                        .requestMatchers("/home").permitAll()

                        // Only ADMINs can add, edit, delete products
                        .requestMatchers("/view/products/add", "/view/products/edit/**", "/view/products/delete/**").hasRole("ADMIN")

                        // Only ADMINs can add, edit, delete categories
                        .requestMatchers("/view/categories/add", "/view/categories/edit/**", "/view/categories/delete/**").hasRole("ADMIN")

                        // Products and categories view pages require authentication (ADMIN or USER)
                        .requestMatchers("/view/products").authenticated()
                        .requestMatchers("/view/categories").authenticated()

                        // Any other requests must also be authenticated
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        // After successful login, redirect to /home
                        .defaultSuccessUrl("/home", true)
                        // Allow everyone to access the login page
                        .permitAll()
                )
                .logout(logout -> logout
                        // On logout, redirect to /home
                        .logoutSuccessUrl("/home")
                        .permitAll()
                );

        // Return the configured security filter chain
        return http.build();
    }

    // Configures how users will be loaded — from a JDBC database
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource); // Uses default queries on "users" and "authorities" tables
    }

    // Bean for password encoding — uses BCrypt hashing algorithm
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Optional utility method for generating a hashed password (you run this manually if needed)
    public static void main(String[] args) {
        String passwordUser = "user123";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(passwordUser);
        System.out.println(encodedPassword); // Output the hashed password to insert into the DB
    }
}
