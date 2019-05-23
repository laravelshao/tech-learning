package com.laravelshao.learning.concurrent.waitnotify;

/**
 * Created by shaoqinghua on 2018/5/3.
 */
public class Productor implements Runnable {

    private Resource r;

    public Productor(Resource r) {
        this.r = r;
    }

    @Override
    public void run() {
        //给资源仓库保存数据
        while (true) {
            r.save("汽车");
        }
    }
}
