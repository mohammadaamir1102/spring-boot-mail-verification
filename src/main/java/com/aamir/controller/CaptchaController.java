package com.aamir.controller;

import cn.apiclub.captcha.Captcha;
import com.aamir.util.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/vi/captcha")
public class CaptchaController {

    @Autowired
    private CaptchaUtil captchaUtil;

    @GetMapping("getCaptcha")
    public List<String> generatedEncodedCaptcha() {
        List<String> responseList = new ArrayList<>();
        try {
            Captcha captcha = captchaUtil.createCaptcha(240, 70);
            responseList.add(Base64.getEncoder().withoutPadding().encodeToString(captcha.getAnswer().getBytes()));
            responseList.add(captchaUtil.encodeCaptcha(captcha));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return responseList;
    }
}
