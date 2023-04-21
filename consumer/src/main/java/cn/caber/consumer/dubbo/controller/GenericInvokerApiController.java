package cn.caber.consumer.dubbo.controller;

import cn.caber.dubbo.po.Caber;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 泛化调用测试
 */
@RestController
@RequestMapping("/generic")
public class GenericInvokerApiController {


    @GetMapping("/invoke")
    public Caber getObject(@RequestParam String name) {
        String[] strings = {String.class.getTypeName()};
        Map map = (Map) GenericInvoke("cn.caber.dubbo.service.generic.GenericTestService",
                "V1.0",
                "generic",
                "invoke",
                new String[]{String.class.getTypeName()},
                new Object[]{"caber"});
        // 泛化调用强制返回map,如果业务需要自定义类型，则需要实现自定义返回值反序列化逻辑
        return Caber.builder().name((String) map.get("name")).age((Integer) map.get("age")).build();

    }

    public Object GenericInvoke(String interfaceName, String version, String group, String methodName, String[] paramTypes, Object[] params) {
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();

        reference.setInterface(interfaceName);
        reference.setGroup(group);
        reference.setVersion(version);
        reference.setRetries(0);
        // 声明为泛化接口
        reference.setGeneric("true");
        reference.setTimeout(3000);
        reference.setCheck(false);
        // dubbo 原生做了一层genericService 的缓存，缓存key从reference中生成，
        GenericService genericService = ReferenceConfigCache.getCache().get(reference);
        Object res = genericService.$invoke(methodName, paramTypes, params);
        System.out.println("result[caber]：" + res);
        return res;
    }
}
