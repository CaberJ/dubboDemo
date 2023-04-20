package cn.caber.provider.dubbo.serviceImpl.generic;

import cn.caber.dubbo.po.Caber;
import cn.caber.dubbo.service.generic.GenericTestService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(interfaceClass = GenericTestService.class,version = "V1.0",group = "generic")
public class GenericTestServiceImpl implements GenericTestService {

    @Override
    public Caber invoke(String name) {
        return Caber.builder().age(12).name(name).build();
    }
}
