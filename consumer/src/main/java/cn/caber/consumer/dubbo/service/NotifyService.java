package cn.caber.consumer.dubbo.service;

import cn.caber.dubbo.po.Caber;

public interface NotifyService {
    void onReturn(Caber caber);
    void onThrow(Throwable ex, String name);
    void onInvoke(String name);
}
