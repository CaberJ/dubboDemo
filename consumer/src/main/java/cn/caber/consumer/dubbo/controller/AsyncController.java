package cn.caber.consumer.dubbo.controller;

import cn.caber.dubbo.po.Caber;
import cn.caber.dubbo.service.async.AsyncService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 异步调用测试
 */
@RestController
@RequestMapping("/async")
public class AsyncController {

    @DubboReference
    private AsyncService asyncService;


    @GetMapping("/invoke")
    public Caber invoke(@RequestParam String name) {
        Caber caber = asyncService.invoke(name);
        return caber;
    }

    @GetMapping("/asyncInvoke")
    public Caber asyncInvoke(@RequestParam String name) throws ExecutionException, InterruptedException {
        CompletableFuture<Caber> caberCompletableFuture = asyncService.asyncInvoke(name);
        System.out.println("任务已提交");
        return caberCompletableFuture.whenComplete((v, t) -> {
            if (Objects.nonNull(t)) {
                t.printStackTrace();
            }
        }).get();
    }

    @GetMapping("/asyncInvokeByRpcContext")
    public Caber asyncInvokeByRpcContext(@RequestParam String name) throws ExecutionException, InterruptedException {
        Caber caber = asyncService.asyncInvokeByRpcContext(name);
        System.out.println(caber);
        //拿到调用的Future引用，当结果返回后，会被通知和设置到此Future
        CompletableFuture<Object> completableFuture = RpcContext.getServerContext().getCompletableFuture();
        return (Caber) completableFuture.whenComplete((result, exception) -> {
            if (exception == null) {
                // 执行业务
            } else {
                exception.printStackTrace();
            }
        }).get();

        // 如果是函数式接口，则可使用下面这种方式；此处如果传入一个callable 接口服务，则可得到一个CompletableFuture<Object> 返回
       /* CompletableFuture<Object> completableFuture = RpcContext.getServerContext().asyncCall(
                () -> {
            asyncService.asyncInvokeByRpcContext(name);
        });
        completableFuture.get();
        */
    }

    @GetMapping("/asyncConsume")
    public Caber asyncConsume(@RequestParam String name) throws ExecutionException, InterruptedException {
        CompletableFuture<Caber> future = CompletableFuture.supplyAsync(() -> {
            return asyncService.invoke(name);
        });
        return future.whenComplete((v, t) -> {
            if (Objects.nonNull(t)) {
                t.printStackTrace();
            }
        }).get();
    }

}
