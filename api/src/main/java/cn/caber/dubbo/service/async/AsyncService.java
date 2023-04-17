package cn.caber.dubbo.service.async;

import cn.caber.dubbo.po.Caber;

import java.util.concurrent.CompletableFuture;

public interface AsyncService {

    Caber invoke(String name);

    CompletableFuture<Caber> asyncInvoke(String name);

    Caber asyncInvokeByRpcContext(String name);
}
