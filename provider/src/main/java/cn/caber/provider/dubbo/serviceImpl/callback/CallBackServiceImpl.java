package cn.caber.provider.dubbo.serviceImpl.callback;

import cn.caber.dubbo.po.Caber;
import cn.caber.dubbo.service.callback.CallBackService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(interfaceClass = CallBackService.class)
public class CallBackServiceImpl implements CallBackService {

    @Override
    public Caber invoke(String name) {
        if("aa".equals(name)){
            throw new RuntimeException("测试异常");
        }
        return Caber.builder().age(12).name(name).build();
    }
}
