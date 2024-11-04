package com.wclsolution.docpal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/grpc")
public class GrpcController {

    private final ClientService clientService;

    public GrpcController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping
    public ResultData<String> get() {
        return ResultData.success("hello");
    }

    @GetMapping("/get/{name}")
    public ResultData<Object> grpc(@PathVariable("name") String name) {
        StringBuilder stringBuilder = new StringBuilder(name);
        // 模拟入参超过默认 4M 的情况下，需要在配置文件中修改 默认入参大小
//        for (int i = 0; i < 500; i++) {
//            stringBuilder.append(Math.random());
//        }
        stringBuilder.append(Math.random());
        String names = clientService.receiveGreeting(stringBuilder.toString());
        return ResultData.success(names);
    }

}
