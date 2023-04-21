package cn.caber.consumer.dubbo.service;

import cn.caber.dubbo.po.Caber;
import org.springframework.stereotype.Component;

@Component("notifyService")
public class NotifyServiceImpl implements NotifyService {

    @Override
    public void onReturn(Caber caber) {
        System.out.println("远程服务正常返回"+caber.toString());
    }

    @Override
    public void onThrow(Throwable ex, String name) {
        ex.printStackTrace();
        System.out.println("远程服务异常抛错了");
    }

    @Override
    public void onInvoke(String name) {
        System.out.println("即将调用远程服务...");
    }
}
