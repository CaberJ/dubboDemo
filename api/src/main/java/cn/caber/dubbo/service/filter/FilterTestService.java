package cn.caber.dubbo.service.filter;

import cn.caber.dubbo.po.Caber;

public interface FilterTestService {
    Caber invoke(String name);
}
