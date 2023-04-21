package cn.caber.consumer.dubbo.controller;

import cn.caber.dubbo.po.Caber;
import cn.caber.dubbo.service.filter.FilterTestService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 过滤器
 */
@RestController
@RequestMapping("/filter")
public class FilterController {

    @DubboReference
    private FilterTestService filterTestService;

    @GetMapping("/invoke")
    public Caber invoke(@RequestParam String name) {
        Caber caber = filterTestService.invoke(name);
        return caber;
    }

}
