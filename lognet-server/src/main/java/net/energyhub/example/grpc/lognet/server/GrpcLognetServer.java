package net.energyhub.example.grpc.lognet.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcLognetServer {

  public static void main(String[] args) {
    SpringApplication.run(GrpcLognetServer.class, args);
  }
}
