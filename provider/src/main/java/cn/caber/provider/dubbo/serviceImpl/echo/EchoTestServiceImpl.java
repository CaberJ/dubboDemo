package cn.caber.provider.dubbo.serviceImpl.echo;

import cn.caber.dubbo.po.Caber;
import cn.caber.dubbo.service.echo.EchoTestService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(interfaceClass = EchoTestService.class)
public class EchoTestServiceImpl implements EchoTestService {

    @Override
    public Caber invoke(String name) {
        return Caber.builder().age(23).name(name).build();
    }
}
