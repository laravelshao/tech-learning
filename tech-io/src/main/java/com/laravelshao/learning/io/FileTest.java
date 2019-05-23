package com.laravelshao.learning.io;

/**
 * Created by LaravelShao on 2017/12/14.
 */
public class FileTest {

    public static void main(String[] args) {

        /**
         * File方法
         * 
         * public boolean createNewFile() 创建文件，存在则不创建，创建文件的父目录一定要存在
         * public boolean mkdir() 创建文件夹，存在则不创建
         * public boolean mkdirs() 创建文件夹，父文件夹不存在则也会创建，存在则不创建
         * public boolean delete() 删除指定路径下的文件或文件夹
         *     ①创建的文件或文件夹不带盘符，默认在项目路径下
         *     ②Java中的删除不走回收站
         *     ③要删除文件夹，文件夹内不能有文件或文件夹
         * public boolean renameTo(File dest) 重命名指定路径下的文件（路径名相同，就是改名；路径名不同，就是改名并剪切）
         * public boolean isDirectory() 判断是否是目录
         * public boolean isFile() 判断是否是文件
         * public boolean exists() 判断是否存在
         * public boolean canRead() 判断是否可读
         * public boolean canWrite() 判断是否可写
         * public boolean isHidden() 判断是否隐藏
         * public String getAbsolutePath() 获取绝对路径
         * public File getAbsoluteFile() 获取封装绝对路径的File路径
         * public String getCanonicalPath() 获取规范的绝对路径
         * public File getCanonicalFile() 获取封装规范绝对路径的File路径
         * public String getPath() 获取相对路径
         * public String getName() 获取名称
         * public long length() 获取长度，字节数
         * public long lastModified() 获取最后一次的修改时间，毫秒值
         * public static File[] listRoots() 列出可用的文件系统根
         */


    }
}
