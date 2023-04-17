package cn.caber.dubbo.po;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Caber implements Serializable {
    private String name;
    private Integer age;
}
