package cn.caber.consumer.dubbo.controller;

import cn.caber.dubbo.po.Caber;
import cn.caber.dubbo.service.async.AsyncService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
        return caberCompletableFuture.get();
    }

    @GetMapping("/asyncInvokeByRpcContext")
    public Caber asyncInvokeByRpcContext(@RequestParam String name) {
        Caber caber = asyncService.asyncInvokeByRpcContext(name);
        return caber;
    }


}
