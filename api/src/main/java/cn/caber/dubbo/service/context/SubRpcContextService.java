package cn.caber.dubbo.service.context;

import cn.caber.dubbo.po.Caber;

public interface SubRpcContextService {

    Caber subInvoke(String name);
}
