package com.laravelshao.learning.io;

/**
 * Created by LaravelShao on 2017/12/14.
 */
public class CopyTextFileTest {

    public static void main(String[] args) {
        /**
         * 复制文本文件的5种方式
         */


        /**
         * ①基本字符流一次读写一个字符
         */
          //FileReader fr = new FileReader(srcString);
          //FileWriter fw = new FileWriter(destString);
          //
          //int ch = 0;
          //while ((ch = fr.read()) != -1) {
          //    fw.write(ch);
          //}
          //
          //fw.close();
          //fr.close();


        /**
         * ②基本字符流一次读写一个字符数组
         */
        //FileReader fr = new FileReader(srcString);
        //FileWriter fw = new FileWriter(destString);
        //
        //char[] chs = new char[1024];
        //int len = 0;
        //while ((len = fr.read(chs)) != -1) {
        //    fw.write(chs, 0, len);
        //}
        //
        //fw.close();
        //fr.close();

        /**
         * ③字符缓冲流一次读写一个字符
         */
        //BufferedReader br = new BufferedReader(new FileReader(srcString));
        //BufferedWriter bw = new BufferedWriter(new FileWriter(destString));
        //
        //int ch = 0;
        //while ((ch = br.read()) != -1) {
        //    bw.write(ch);
        //}
        //
        //bw.close();
        //br.close();


        /**
         * ④字符缓冲流一次读写一个字符数组
         */
        //BufferedReader br = new BufferedReader(new FileReader(srcString));
        //BufferedWriter bw = new BufferedWriter(new FileWriter(destString));
        //
        //char[] chs = new char[1024];
        //int len = 0;
        //while ((len = br.read(chs)) != -1) {
        //    bw.write(chs, 0, len);
        //}
        //
        //bw.close();
        //br.close();


        /**
         * ⑤字符缓冲流一次读写一个字符串
         */
        //BufferedReader br = new BufferedReader(new FileReader(srcString));
        //BufferedWriter bw = new BufferedWriter(new FileWriter(destString));
        //
        //String line = null;
        //while ((line = br.readLine()) != null) {
        //    bw.write(line);
        //    bw.newLine();
        //    bw.flush();
        //}
        //
        //bw.close();
        //br.close();

        
    }
}
