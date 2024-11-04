package com.wclsolution.docpal;

import com.wclsolution.docpal.grpc.example.HelloRequest;
import com.wclsolution.docpal.grpc.example.MyServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    /**
     * 这里的名称为 proto 文件中 service 对应的名称，注意首字母小写
     */
    // 初始化student-rpc-server对应的stub，如果需要多个可以在这里注入
    @GrpcClient("student-rpc-server")
    private MyServiceGrpc.MyServiceBlockingStub myServiceStub;

    public String receiveGreeting(String name) {
        HelloRequest request = HelloRequest.newBuilder()
                .setName(name)
                .build();
        myServiceStub.withMaxInboundMessageSize(Integer.MAX_VALUE);
        myServiceStub.withMaxOutboundMessageSize(Integer.MAX_VALUE);
        return myServiceStub.sayHello(request).getMessage();
    }

}
