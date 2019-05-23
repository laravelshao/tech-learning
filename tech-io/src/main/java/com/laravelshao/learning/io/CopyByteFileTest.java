package com.laravelshao.learning.io;

/**
 * Created by LaravelShao on 2017/12/14.
 */
public class CopyByteFileTest {

    public static void main(String[] args) {

        /**
         * 复制字节文件的4种方式
         */

        /**
         * ①基本字节流一次读写一个字节
         */
        //FileInputStream fis = new FileInputStream(srcString);
        //FileOutputStream fos = new FileOutputStream(destString);
        //
        //int by = 0;
        //while ((by = fis.read()) != -1) {
        //    fos.write(by);
        //}
        //
        //fos.close();
        //fis.close();

        /**
         * ②基本字节流一次读写一个字节数组
         */
        //FileInputStream fis = new FileInputStream(srcString);
        //FileOutputStream fos = new FileOutputStream(destString);
        //
        //byte[] bys = new byte[1024];
        //int len = 0;
        //while ((len = fis.read(bys)) != -1) {
        //    fos.write(bys, 0, len);
        //}
        //
        //fos.close();
        //fis.close();


        /**
         * ③字节缓冲流一次读写一个字节
         */
        //BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcString));
        //BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destString));
        //
        //int by = 0;
        //while ((by = bis.read()) != -1) {
        //    bos.write(by);
        //}
        //
        //bos.close();
        //bis.close();

        /**
         * ④字节缓冲流一次读写一个字节数组
         */

        //BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcString));
        //BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destString));
        //
        //byte[] bys = new byte[1024];
        //int len = 0;
        //while ((len = bis.read(bys)) != -1) {
        //    bos.write(bys, 0, len);
        //}
        //
        //bos.close();
        //bis.close();


    }
}
