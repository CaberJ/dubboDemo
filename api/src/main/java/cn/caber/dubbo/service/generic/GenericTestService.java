package cn.caber.dubbo.service.generic;

import cn.caber.dubbo.po.Caber;

public interface GenericTestService {

    Caber invoke(String name);
}
