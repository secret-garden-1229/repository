package org.zuel.crm.workbench.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zuel.crm.workbench.domain.FunnelVO;
import org.zuel.crm.workbench.service.TranService;

import java.util.List;

@Controller
public class ChartController {

    @Autowired
    private TranService tranService;

    /**
     * 跳转交易统计图标页面
     * @return
     */
    @RequestMapping("/workbench/chart/transaction/toTranIndex.do")
    public String toTranIndex(){
        return "workbench/chart/transaction/index";
    }


    /**
     * 以组为单位查询 交易表中各个阶段的 数量
     * @return
     */
    @RequestMapping("/workbench/chart/transaction/queryCountOfTranGroupByStage.do")
    public @ResponseBody Object queryCountOfTranGroupByStage(){
        List<FunnelVO> funnelVOList = tranService.queryCountOfTranGroupByStage();
        return funnelVOList;
    }

}
