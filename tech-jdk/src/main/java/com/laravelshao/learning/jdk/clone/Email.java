package com.laravelshao.learning.jdk.clone;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qinghua.shao
 * @date 2019/1/6
 */
@Data
public class Email implements Cloneable, Serializable {
    private static final long serialVersionUID = -9005686438484364461L;

    private String content;

    @Override
    protected Email clone() throws CloneNotSupportedException {
        return (Email) super.clone();
    }
}
