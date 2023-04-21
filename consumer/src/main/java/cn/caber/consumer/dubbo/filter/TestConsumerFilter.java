package cn.caber.consumer.dubbo.filter;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;

@Activate(group = CommonConstants.CONSUMER, order = 10)
public class TestConsumerFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String s = "11111";
        invocation.setAttachment("operateId", s);
        System.out.println("设置操作数据：" + s);
        return invoker.invoke(invocation);
    }
}
