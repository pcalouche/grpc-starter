package net.energyhub.example.grpc.lognet.server.config;

import org.lognet.springboot.grpc.security.GrpcSecurity;
import org.lognet.springboot.grpc.security.GrpcSecurityConfigurerAdapter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

// @Configuration
public class CustomGrpcSecurityConfigurerAdapter extends GrpcSecurityConfigurerAdapter {

  private final DaoAuthenticationProvider daoAuthenticationProvider;

  public CustomGrpcSecurityConfigurerAdapter(DaoAuthenticationProvider daoAuthenticationProvider) {
    this.daoAuthenticationProvider = daoAuthenticationProvider;
  }

  @Override
  public void configure(GrpcSecurity builder) throws Exception {
    builder
        .authorizeRequests()
        .anyMethod()
        .authenticated()
        .and()
        .authenticationProvider(daoAuthenticationProvider);
  }
}
