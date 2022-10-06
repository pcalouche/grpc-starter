package net.energyhub.example.grpc.lognet.server.service;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.pcalouche.protos.GreetingRequest;
import com.pcalouche.protos.GreetingResponse;
import com.pcalouche.protos.GreetingServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.security.access.prepost.PreAuthorize;

@GRpcService
@Slf4j
public class GreetingService extends GreetingServiceGrpc.GreetingServiceImplBase {
    @PreAuthorize("@permissionService.block(#principal)") // does not work
//  @PreAuthorize("hasRole('USER')") // works as expected
  public void sayHello(GreetingRequest request, StreamObserver<GreetingResponse> responseObserver) {
    try {
      log.info(JsonFormat.printer().print(request));
    } catch (InvalidProtocolBufferException e) {
      throw new RuntimeException(e);
    }
    GreetingResponse reply =
        GreetingResponse.newBuilder().setMessage("Acknowledging " + request.getMessage()).build();
    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }
}
