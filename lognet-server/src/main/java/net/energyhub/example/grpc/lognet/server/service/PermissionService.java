package net.energyhub.example.grpc.lognet.server.service;

import org.springframework.security.core.AuthenticatedPrincipal;

/**
 * Permission service bean that can be used in PreAuthorize annotations that in theory could be used
 * to handle more complex cases or reference other beans to help with authorization.
 */
public class PermissionService {
  public boolean allow() {
    return true;
  }

  public boolean block() {
    return false;
  }

  public boolean allow(AuthenticatedPrincipal principal) {
    // more complex stuff could be here
    return true;
  }

  public boolean block(AuthenticatedPrincipal principal) {
    // more complex stuff could be here
    return false;
  }
}
