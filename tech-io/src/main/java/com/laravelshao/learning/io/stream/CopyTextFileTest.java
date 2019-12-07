package com.laravelshao.learning.io.stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 复制文本文件的5种方式
 *
 * @author qinghua.shao
 * @date 2017/12/14
 * @since 1.0.0
 */
public class CopyTextFileTest {

    public static void main(String[] args) throws Exception{

        //method1("./srcFile", "./destFile");
        //method2("./srcFile", "./destFile");
        //method3("./srcFile", "./destFile");
        //method4("./srcFile", "./destFile");
        //method5("./srcFile", "./destFile");
    }

    /**
     * ①基本字符流一次读写一个字符
     */
    public static void method1(String srcString, String destString) throws IOException {

        FileReader fr = new FileReader(srcString);
        FileWriter fw = new FileWriter(destString);

        int ch = 0;
        while ((ch = fr.read()) != -1) {
            fw.write(ch);
        }

        fw.close();
        fr.close();
    }

    /**
     * ②基本字符流一次读写一个字符数组
     */
    public static void method2(String srcString, String destString) throws IOException {

        FileReader fr = new FileReader(srcString);
        FileWriter fw = new FileWriter(destString);

        char[] chs = new char[1024];
        int len = 0;
        while ((len = fr.read(chs)) != -1) {
            fw.write(chs, 0, len);
        }

        fw.close();
        fr.close();
    }

    /**
     * ③字符缓冲流一次读写一个字符
     */
    public static void method3(String srcString, String destString) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(srcString));
        BufferedWriter bw = new BufferedWriter(new FileWriter(destString));

        int ch = 0;
        while ((ch = br.read()) != -1) {
            bw.write(ch);
        }

        bw.close();
        br.close();
    }

    /**
     * ④字符缓冲流一次读写一个字符数组
     */
    public static void method4(String srcString, String destString) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(srcString));
        BufferedWriter bw = new BufferedWriter(new FileWriter(destString));

        char[] chs = new char[1024];
        int len = 0;
        while ((len = br.read(chs)) != -1) {
            bw.write(chs, 0, len);
        }

        bw.close();
        br.close();
    }

    /**
     * ⑤字符缓冲流一次读写一个字符串
     */
    public static void method5(String srcString, String destString) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(srcString));
        BufferedWriter bw = new BufferedWriter(new FileWriter(destString));

        String line = null;
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
            bw.flush();
        }

        bw.close();
        br.close();
    }
}
