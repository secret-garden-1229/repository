package org.zuel.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorkbenchIndexController {

    /**
     * 跳转到业务首页
     * @return
     */
    @GetMapping("/workbench/index.do")
    public String index(){
        return "workbench/index";
    }
}
