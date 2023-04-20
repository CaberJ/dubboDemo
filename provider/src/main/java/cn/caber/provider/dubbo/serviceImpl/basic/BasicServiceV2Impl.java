package cn.caber.provider.dubbo.serviceImpl.basic;

import cn.caber.dubbo.po.Caber;
import cn.caber.dubbo.service.basic.BasicService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(interfaceClass = BasicService.class,version = "V2.0",group = "normal")
public class BasicServiceV2Impl implements BasicService {
    @Override
    public Caber invoke(Caber caber) {
        caber.setAge(12);
        caber.setName("v2");
        return caber;
    }

}
