package cn.caber.dubbo.service.context;

import cn.caber.dubbo.po.Caber;

public interface RpcContextService {

    Caber invoke(String name);
}
