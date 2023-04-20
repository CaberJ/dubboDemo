package cn.caber.provider.dubbo.serviceImpl.context;

import cn.caber.dubbo.po.Caber;
import cn.caber.dubbo.service.context.RpcContextService;
import cn.caber.dubbo.service.context.SubRpcContextService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;

@DubboService(interfaceClass = RpcContextService.class)
public class RpcContextServiceImpl implements RpcContextService {

    @DubboReference
    private SubRpcContextService subRpcContextService;

    //在 Dubbo 2.7 中，在 A 端设置的参数，调用 B 以后，如果 B 继续调用了 C，原来在 A 中设置的参数也会被带到 C 端过去，造成参数污染的问题。
    // Dubbo 3 对 RpcContext 进行了重构，支持可选参数透传，默认开启参数透传。
    @Override
    public Caber invoke(String name) {
        RpcContext context = RpcContext.getContext();
        boolean providerSide = context.isProviderSide();
        System.out.println("side:" + providerSide);
        // 获取调用方IP地址
        String clientIP = context.getRemoteHost();
        System.out.println("serverContext:" + clientIP);
        Integer age = (Integer) RpcContext.getContext().getObjectAttachment("age");
        System.out.println("RpcContext param:" + age);
        name = RpcContext.getContext().getAttachment("name");
        // 注意：每发起RPC调用，上下文状态会变化
        Caber caber = subRpcContextService.subInvoke(name);
        // 此时本端变成消费端，这里会返回false
        boolean isProviderSide = RpcContext.getContext().isProviderSide();
        System.out.println("side:" + isProviderSide);
        return caber;

    }


    /**
     *
     * 在 Dubbo 3 中，RpcContext 被拆分为四大模块（ServerContext、ClientAttachment、ServerAttachment 和 ServiceContext）。
     *
     * 它们分别承担了不同的指责：
     *
     * ServiceContext：在 Dubbo 内部使用，用于传递调用链路上的参数信息，如 invoker 对象等
     * ClientAttachment：在 Client 端使用，往 ClientAttachment 中写入的参数将被传递到 Server 端
     * ServerAttachment：在 Server 端使用，从 ServerAttachment 中读取的参数是从 Client 中传递过来的
     * ServerContext：在 Client 端和 Server 端使用，用于从 Server 端回传 Client 端使用，Server 端写入到 ServerContext 的参数在调用结束后可以在 Client 端的 ServerContext 获取到
     */
}
