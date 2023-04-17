//package cn.caber.consumer.dubbo.controller;
//
//import cn.caber.dubbo.po.Caber;
//import cn.caber.dubbo.service.basic.BasicService;
//import org.apache.dubbo.config.annotation.DubboReference;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/dubbo/generic")
//public class GenericInvokeController {
//
//    @DubboReference(generic = true, interfaceName = "cn.caber.dubbo.api.service.CaberDubboService", version = "V1.0", group = "normal")
//    private BasicService basicService;
//
//    @GetMapping("/getObject")
//    public Object getObject() {
//        return basicService.getObject(Caber.builder().build());
//    }
//
//
//}
