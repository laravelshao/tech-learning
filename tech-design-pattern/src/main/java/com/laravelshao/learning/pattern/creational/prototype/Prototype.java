package com.laravelshao.learning.pattern.creational.prototype;

/**
 * @author qinghua.shao
 * @date 2018/8/5
 * @since 1.0.0
 */
public class Prototype implements Cloneable {

    //private ArrayList list = new ArrayList();

    @Override
    public Prototype clone() {
        Prototype prototype = null;
        try {
            prototype = (Prototype) super.clone();
            //prototype.list = (ArrayList) this.list.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return prototype;
    }
}
