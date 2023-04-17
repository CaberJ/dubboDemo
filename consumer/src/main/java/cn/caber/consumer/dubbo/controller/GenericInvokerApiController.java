package cn.caber.consumer.dubbo.controller;

import cn.caber.dubbo.po.Caber;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dubbo/generic/api")
public class GenericInvokerApiController {


    @GetMapping("/dubboInvoke")
    public Caber getObject() {
        return (Caber) GenericInvoke("cn.caber.dubbo.api.service.CaberDubboService", "V1.0", "normal", "getObject");
    }

    public Object GenericInvoke(String interfaceName, String version, String group, String methodName) {
        // 引用远程服务
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        // 弱类型接口名
        reference.setInterface(interfaceName);
        reference.setGroup(group);
        reference.setVersion(version);
        reference.setRetries(0);
        // RpcContext中设置generic=gson
//        RpcContext.getContext().setAttachment("generic", "gson");
        // 声明为泛化接口
        reference.setGeneric("true");
        reference.setCheck(false);

        GenericService genericService = ReferenceConfigCache.getCache().get(reference);
        // 传递参数对象的json字符串进行一次调用
        Object res = genericService.$invoke(methodName, new String[]{"cn.caber.dubbo.api.po.Caber"}, new Object[]{Caber.builder().build()});
        System.out.println("result[caber]：" + res);
        return res;
    }
}
