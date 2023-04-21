package cn.caber.provider.dubbo.serviceImpl.filter;

import cn.caber.dubbo.po.Caber;
import cn.caber.dubbo.service.filter.FilterTestService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(interfaceClass = FilterTestService.class)
public class FilterServiceImpl implements FilterTestService {
    @Override
    public Caber invoke(String name) {
        return Caber.builder().age(12).name(name).build();
    }
}
