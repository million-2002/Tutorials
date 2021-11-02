package com.demo.newproject.controller;

import com.demo.newproject.service.UserService;
import com.demo.newproject.util.jsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @PostMapping(value = "/login", produces = {"application/json;charset=UTF-8"})
    public String login(@RequestBody Map<String, String> map) {
        String account = map.get("account");
        String password = map.get("password");
        Map<String, Object> resMap = userService.Login(account, password);
        return jsonUtil.getJSONString(0, resMap);
    }

    @PostMapping(value = "/register", produces = {"application/json;charset=UTF-8"})
    public String register(@RequestBody Map<String, String> map) {
        String account = map.get("account");
        String password = map.get("password");
        Map<String, Object> resMap = userService.register(account, password);
        return jsonUtil.getJSONString(0, resMap);
    }

    @PostMapping(value = "/upload", produces = {"application/json;charset=UTF-8"})
    public String uploadRegister(HttpServletRequest httpServletRequest) {
        String filePath = "/Users/hanchenzhu/Pictures/img/";
        MultipartFile file = ((MultipartHttpServletRequest) httpServletRequest).getFiles("file").get(0);
        if(file.isEmpty()) {
            return jsonUtil.getJSONString(999, "upload error");
        }
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffixName;
        int userId = Integer.parseInt(httpServletRequest.getParameter("userId"));
        int res = userService.updateHead_url("http://127.0.0.1:8080/image/" + fileName, userId);
        File dest = new File(filePath + fileName);
        if(!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            logger.error(e.getMessage());
            return jsonUtil.getJSONString(999, "上传失败");
        }
        return jsonUtil.getJSONString(0, res);
    }
}
