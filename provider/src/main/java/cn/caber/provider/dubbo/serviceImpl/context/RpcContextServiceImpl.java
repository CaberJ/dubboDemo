package cn.caber.provider.dubbo.serviceImpl.context;

import cn.caber.dubbo.po.Caber;
import cn.caber.dubbo.service.context.RpcContextService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;

@DubboService(interfaceClass = RpcContextService.class)
public class RpcContextServiceImpl implements RpcContextService {

    @Override
    public Caber invoke(String name) {
        Integer age = (Integer) RpcContext.getContext().getObjectAttachment("age");
        name = RpcContext.getContext().getAttachment("name");
        return Caber.builder().age(age).name(name).build();
    }
}
