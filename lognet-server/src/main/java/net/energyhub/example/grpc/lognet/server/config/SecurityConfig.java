package net.energyhub.example.grpc.lognet.server.config;

import net.energyhub.example.grpc.lognet.server.service.PermissionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
  @Bean
  public PermissionService permissionService() {
    return new PermissionService();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(4);
  }

  @Bean
  public InMemoryUserDetailsManager inMemoryUserDetailsService(PasswordEncoder passwordEncoder) {
    UserDetails admin =
        User.withUsername("admin")
            .password(passwordEncoder.encode("password"))
            .roles("ADMIN")
            .build();
    UserDetails user =
        User.withUsername("user")
            .password(passwordEncoder.encode("password"))
            .roles("USER")
            .build();
    return new InMemoryUserDetailsManager(admin, user);
  }

  @Bean
  public DaoAuthenticationProvider inMemoryDaoAuthenticationProvider(
      InMemoryUserDetailsManager inMemoryUserDetailsService, PasswordEncoder passwordEncoder) {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(inMemoryUserDetailsService);
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
    return daoAuthenticationProvider;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests().anyRequest().authenticated().and().httpBasic();

    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
}
