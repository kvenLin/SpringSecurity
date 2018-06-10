package net.javablog.ctrl;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CtrlProtected {


    @ResponseBody
    @RequestMapping("/testjwt")
    public Map testjwt() {
        Map map = new HashMap<>();
        map.put("data", "abc");
        return map;
    }


}
