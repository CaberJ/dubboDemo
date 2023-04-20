package cn.caber.consumer.dubbo.controller;


import cn.caber.dubbo.po.Caber;
import cn.caber.dubbo.service.context.RpcContextService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rpcContext")
public class RpcContextController {

    @DubboReference
    private RpcContextService rpcContextService;


    @GetMapping("/invoke")
    public Caber invoke(@RequestParam String name) {
        RpcContext.getContext().setAttachment("name", name);
        RpcContext.getContext().setAttachment("age", 34);
        Caber caber = rpcContextService.invoke(null);
        RpcContext context = RpcContext.getContext();
        System.out.println(context.toString());
        return caber;
    }
}
