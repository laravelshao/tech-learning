package com.laravelshao.springmvc.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("file")
@Controller
public class FileUploadController {

    /**
     * 
     * @param multipartFile
     * @return 返回的String是视图名，返回的视图名以redirect:开头，做页面重定向
     * @throws Exception
     */
    @RequestMapping("upload")
    public String upload(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        if (multipartFile != null) {
            // multipartFile.getOriginalFilename() 获取文件的原始名称
            multipartFile.transferTo(new File("e:\\tmp\\" + multipartFile.getOriginalFilename()));
        }
        return "redirect:/html/success.html";
    }
}