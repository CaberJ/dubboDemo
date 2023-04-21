package cn.caber.provider.dubbo.filter;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;

@Activate(group = CommonConstants.PROVIDER, order = 10)
public class TestProviderFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String operateId = invocation.getAttachment("operateId");
        // 此数据可被provider设置进上下文，
        System.out.println("获取到操作数据：" + operateId);
        try {
            return invoker.invoke(invocation);
        } finally {
            // 执行完后，从上下文中删除设置的数据
        }
    }
}
