package com.laravelshao.learning.concurrent.waitnotify;

/**
 * Created by shaoqinghua on 2018/5/3.
 */
public class Consumer implements Runnable {

    private Resource r;

    public Consumer(Resource r) {
        this.r = r;
    }

    @Override
    public void run() {
        //从资源仓库取出数据
        while (true) {
            r.get();
        }
    }
}
