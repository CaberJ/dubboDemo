package cn.caber.dubbo.service.echo;

import cn.caber.dubbo.po.Caber;

public interface EchoTestService {
    Caber invoke(String name);
}
