package com.laravelshao.learning.pattern.prototype;

import java.util.ArrayList;

/**
 * Created by shaoqinghua on 2018/8/5.
 */

class Prototype implements Cloneable {

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
