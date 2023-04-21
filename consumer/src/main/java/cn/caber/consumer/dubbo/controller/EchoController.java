package cn.caber.consumer.dubbo.controller;

import cn.caber.dubbo.service.echo.EchoTestService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.service.EchoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 回声测试测试
 */
@RestController
@RequestMapping("/echo")
public class EchoController {


    @DubboReference
    private EchoTestService echoTestService;

    @GetMapping("/invoke")
    public String invoke(@RequestParam String name) {
        EchoService echoService = (EchoService) echoTestService;
        String result = (String) echoService.$echo("ok");
        System.out.println("echo test result ：" + result);
        return result;
    }
}
