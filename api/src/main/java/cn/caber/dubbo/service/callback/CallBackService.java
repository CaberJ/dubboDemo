package cn.caber.dubbo.service.callback;

import cn.caber.dubbo.po.Caber;

public interface CallBackService {

    Caber invoke(String name);
}
