package org.zuel.crm.workbench.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.zuel.crm.commons.constants.Constants;
import org.zuel.crm.commons.domain.ReturnObject;
import org.zuel.crm.settings.domain.DicValue;
import org.zuel.crm.settings.domain.User;
import org.zuel.crm.settings.service.DicValueService;
import org.zuel.crm.settings.service.UserService;
import org.zuel.crm.workbench.domain.Tran;
import org.zuel.crm.workbench.domain.TranHistory;
import org.zuel.crm.workbench.domain.TranRemark;
import org.zuel.crm.workbench.service.CustomerService;
import org.zuel.crm.workbench.service.TranHistoryService;
import org.zuel.crm.workbench.service.TranRemarkService;
import org.zuel.crm.workbench.service.TranService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@Controller
public class TranController {

    @Autowired
    private DicValueService dicValueService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TranService tranService;

    @Autowired
    private TranHistoryService tranHistoryService;

    @Autowired
    private TranRemarkService tranRemarkService;


    /**
     * 跳到交易首页
     * @return
     */
    @RequestMapping("/workbench/transaction/index.do")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView();
        List<DicValue> transactionTypeList = dicValueService.queryDicValueByTypeCode("transactionType");
        List<DicValue> sourceList = dicValueService.queryDicValueByTypeCode("source");
        List<DicValue> stageList = dicValueService.queryDicValueByTypeCode("stage");

        mav.addObject("transactionTypeList",transactionTypeList);
        mav.addObject("sourceList",sourceList);
        mav.addObject("stageList",stageList);

        mav.setViewName("workbench/transaction/index");

        return mav;
    }

    /**
     * 查询保存页面需要的字典值
     * @return
     */
    @RequestMapping("/workbench/transaction/toSave.do")
    public ModelAndView toSave(){
        ModelAndView mav = new ModelAndView();
        List<User> userList = userService.queryAllUsers();
        List<DicValue> transactionTypeList = dicValueService.queryDicValueByTypeCode("transactionType");
        List<DicValue> sourceList = dicValueService.queryDicValueByTypeCode("source");
        List<DicValue> stageList = dicValueService.queryDicValueByTypeCode("stage");

        mav.addObject("userList",userList);
        mav.addObject("transactionTypeList",transactionTypeList);
        mav.addObject("sourceList",sourceList);
        mav.addObject("stageList",stageList);

        mav.setViewName("workbench/transaction/save");

        return mav;
    }

    /**
     * 根据选择的阶段返回配置文件中的交易 成功可能性
     * @param stageValue
     * @return
     */
    @RequestMapping("/workbench/transaction/getPossibilityByStage.do")
    public @ResponseBody Object getPossibilityByStage(String stageValue){
        ResourceBundle possibilityBundle = ResourceBundle.getBundle("possibility");
        String possibility = possibilityBundle.getString(stageValue);
        return possibility;
    }


    /**
     * 保存页面中 根据简称来获取客户全名
     * @return
     */
    @RequestMapping("/workbench/transaction/queryCustomerNameByName.do")
    public @ResponseBody Object queryCustomerNameByName(String customerName){
        //List<String> customerNameList = customerService.queryAllCustomerName();
        List<String> customerNameList = customerService.queryCustomerNameByName(customerName);

        return customerNameList;
    }

    /**
     * 保存创建的交易
     * @param map
     * @param session
     * @return
     */
    @RequestMapping("/workbench/transaction/saveCreateTran.do")
    //参数中加注解 @RequestParam ，mvc会把前台的键值封装到这个map中
    public @ResponseBody Object saveCreateTran(@RequestParam Map<String ,Object> map, HttpSession session){
        map.put(Constants.SESSION_USER,session.getAttribute(Constants.SESSION_USER));
        ReturnObject returnObject = new ReturnObject();
        try {
            tranService.saveCreateTran(map);
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试...");
        }
        return returnObject;
    }

    /**
     * 交易明细
     * @param id
     * @return
     */
    @RequestMapping("/workbench/transaction/detailTran.do")
    public ModelAndView detailTran(String id){
        ModelAndView mav = new ModelAndView();
        //交易
        Tran tran = tranService.queryTranForDetailById(id);
        //交易备注
        List<TranRemark> tranRemarkList = tranRemarkService.queryTranRemarkForDetailByTranId(id);
        //交易历史
        List<TranHistory> tranHistoryList = tranHistoryService.queryTranHistoryForDetailByTranId(id);
        //可能性分析
        ResourceBundle bundle = ResourceBundle.getBundle("possibility");
        String possibility = bundle.getString(tran.getStage());
        tran.setPossibility(possibility);
        //交易阶段
        List<DicValue> stageList = dicValueService.queryDicValueByTypeCode("stage");
        //return
        mav.addObject("tran",tran);
        mav.addObject("stageList",stageList);
        mav.addObject("tranRemarkList",tranRemarkList);
        mav.addObject("tranHistoryList",tranHistoryList);
        mav.setViewName("workbench/transaction/detail");
        return mav;
    }
}
