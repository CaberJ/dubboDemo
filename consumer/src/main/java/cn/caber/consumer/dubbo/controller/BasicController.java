package cn.caber.consumer.dubbo.controller;

import cn.caber.dubbo.po.Caber;
import cn.caber.dubbo.service.basic.BasicService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basic")
public class BasicController {

    @DubboReference(version = "V1.0", group = "normal")
    private BasicService basicServiceV1;

    @GetMapping("/getObjectV1")
    public Caber getObjectV1() {
        return basicServiceV1.getObject(Caber.builder().build());
    }

    @DubboReference(version = "V2.0", group = "normal")
    private BasicService basicServiceV2;

    @GetMapping("/getObjectV2")
    public Caber getObjectV2() {
        return basicServiceV2.getObject(Caber.builder().build());
    }
}
