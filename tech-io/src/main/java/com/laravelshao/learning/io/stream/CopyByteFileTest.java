package com.laravelshao.learning.io.stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 复制字节文件的4种方式
 *
 * @author qinghua.shao
 * @date 2017/12/14
 * @since 1.0.0
 */
public class CopyByteFileTest {

    public static void main(String[] args) throws Exception {

        //method1("./srcFile", "./destFile");
        //method2("./srcFile", "./destFile");
        //method3("./srcFile", "./destFile");
        //method4("./srcFile", "./destFile");
    }

    /**
     * ①基本字节流一次读写一个字节
     */
    public static void method1(String srcString, String destString) throws IOException {

        FileInputStream fis = new FileInputStream(srcString);
        FileOutputStream fos = new FileOutputStream(destString);

        int by = 0;
        while ((by = fis.read()) != -1) {
            fos.write(by);
        }

        fos.close();
        fis.close();
    }

    /**
     * ②基本字节流一次读写一个字节数组
     */
    public static void method2(String srcString, String destString) throws IOException {

        FileInputStream fis = new FileInputStream(srcString);
        FileOutputStream fos = new FileOutputStream(destString);

        byte[] bys = new byte[1024];
        int len = 0;
        while ((len = fis.read(bys)) != -1) {
            fos.write(bys, 0, len);
        }

        fos.close();
        fis.close();
    }

    /**
     * ③字节缓冲流一次读写一个字节
     */
    public static void method3(String srcString, String destString) throws IOException {

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcString));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destString));

        int by = 0;
        while ((by = bis.read()) != -1) {
            bos.write(by);
        }

        bos.close();
        bis.close();
    }

    /**
     * ④字节缓冲流一次读写一个字节数组
     */
    public static void method4(String srcString, String destString) throws IOException {

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcString));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destString));

        byte[] bys = new byte[1024];
        int len = 0;
        while ((len = bis.read(bys)) != -1) {
            bos.write(bys, 0, len);
        }

        bos.close();
        bis.close();
    }
}
