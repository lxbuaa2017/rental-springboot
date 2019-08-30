package com.rental.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rental.demo.util.JsonResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static com.rental.demo.util.Constant.ERROR;
import static com.rental.demo.util.Constant.SUCCESS;

@RestController
@RequestMapping("/api")
public class VerifiyCodeController {
    //@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    @ResponseBody
    public int verify(@RequestBody JSONObject jsonObject,
                         HttpSession httpSession) {
        if (httpSession.getAttribute("codeResult") == null) {
            return ERROR;
        } else {
            JSONObject res = (JSONObject) httpSession.getAttribute("codeResult");
            if (!res.get("code").equals(jsonObject.get("code")))
                return ERROR;
            else
                return SUCCESS;
        }
    }
}
