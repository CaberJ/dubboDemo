package cn.caber.provider.dubbo.serviceImpl.async;

import cn.caber.dubbo.po.Caber;
import cn.caber.dubbo.service.async.AsyncService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.AsyncContext;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

@DubboService(interfaceClass = AsyncService.class)
public class AsyncServiceImpl implements AsyncService {

    @Override
    public Caber invoke(String name) {
        try {
            long time = ThreadLocalRandom.current().nextLong(1000);
            Thread.sleep(time);
            StringBuilder s = new StringBuilder();
            s.append("AsyncService invoke param:").append(name).append(",sleep:").append(time);
            return Caber.builder().age(12).name(name).build();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }

    /**
     * 方式一： 使用CompletableFuture 来达成异步效果
     *
     * @param name
     * @return
     */
    @Override
    public CompletableFuture<Caber> asyncInvoke(String name) {
        System.out.println("主线程" + Thread.currentThread().getName());
        return CompletableFuture.supplyAsync(() -> {
            try {
                long time = ThreadLocalRandom.current().nextLong(1000);
                Thread.sleep(time);
                System.out.println("业务线程" + Thread.currentThread().getName() + ",延时：" + time);
                //  此处延时时长超过consumer timeout 后，会报错超时
                return Caber.builder().age(12).name(name).build();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return null;
        });
    }

    @Override
        public Caber asyncInvokeByRpcContext(String name) {
        System.out.println("主线程" + Thread.currentThread().getName());
        final AsyncContext asyncContext = RpcContext.startAsync();
        new Thread(() -> {
            // 如果要使用上下文，则必须要放在第一句执行
            asyncContext.signalContextSwitch();
            Caber caber = Caber.builder().age(12).name(name).build();
            long time = ThreadLocalRandom.current().nextLong(2000);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //  此处延时时长超过consumer timeout 后，会报错超时
            System.out.println("业务线程" + Thread.currentThread().getName() + ",延时：" + time);
            // 写回响应
            asyncContext.write(caber);
        }).start();
        return null;
    }

}
