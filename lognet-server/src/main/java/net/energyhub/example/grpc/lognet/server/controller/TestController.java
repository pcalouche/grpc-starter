package net.energyhub.example.grpc.lognet.server.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

  @PreAuthorize("@permissionService.block(#principal)") // works as expected
  // @PreAuthorize("hasRole('ADMIN')") // works as expected
  @GetMapping
  public String hello() {
    return "Hello";
  }
}
