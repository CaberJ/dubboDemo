package cn.caber.subprovider.dubbo.serviceImpl.context;

import cn.caber.dubbo.po.Caber;
import cn.caber.dubbo.service.context.SubRpcContextService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;

@DubboService(interfaceClass =SubRpcContextService.class )
public class SubRpcContextServiceImpl implements SubRpcContextService {
    @Override
    public Caber subInvoke(String name) {
        Integer age = (Integer) RpcContext.getContext().getObjectAttachment("age");
        //在 Dubbo 2.7 中，在 A 端设置的参数，调用 B 以后，如果 B 继续调用了 C，原来在 A 中设置的参数也会被带到 C 端过去，造成参数污染的问题。
        // 此处能够获取到上一次dubbo调用时传递的参数
        System.out.println("subRpcContext param:" + age);
        return Caber.builder().age(12).name(name).build();
    }
}
