package cn.caber.consumer.dubbo.controller;


import cn.caber.consumer.dubbo.service.NotifyService;
import cn.caber.dubbo.po.Caber;
import cn.caber.dubbo.service.callback.CallBackService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 回调测试
 */
@RestController
@RequestMapping("/callback")
public class CallBackController {

    @DubboReference(methods = @Method(name = "invoke",
            onreturn = "notifyService.onReturn",
            onthrow = "notifyService.onThrow",
        oninvoke = "notifyService.onInvoke"))
    private CallBackService callBackService;

    @Autowired
    private NotifyService notifyService;

    @GetMapping("/invoke")
    public Caber invoke(@RequestParam String name) {
        Caber caber = callBackService.invoke(name);
        return caber;
    }


}
