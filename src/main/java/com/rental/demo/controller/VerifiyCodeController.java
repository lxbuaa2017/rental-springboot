package com.rental.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.rental.demo.util.JsonResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static com.rental.demo.util.Constant.ERROR;
import static com.rental.demo.util.Constant.SUCCESS;

@RestController
@RequestMapping("/")
public class VerifiyCodeController {
    @CrossOrigin
    @RequestMapping(value = "/verify",method = RequestMethod.POST)
    @ResponseBody
    public String verify(@RequestParam("code")String code,
                          HttpSession httpSession){
        if(httpSession.getAttribute("codeResult")==null){
            return JsonResult.build(ERROR,"未发送验证码",null);
        }
        else {
            JSONObject jsonObject=(JSONObject) httpSession.getAttribute("codeResult");
            if(!jsonObject.get("code").equals(code))
                return JsonResult.build(ERROR,"验证码不符",null);
            else
                return JsonResult.build(SUCCESS,"验证码通过",null);
        }
    }
}
