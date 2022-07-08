package org.zuel.crm.workbench.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zuel.crm.commons.constants.Constants;
import org.zuel.crm.commons.domain.ReturnObject;
import org.zuel.crm.commons.utils.DateUtils;
import org.zuel.crm.commons.utils.UUIDUtils;
import org.zuel.crm.settings.domain.User;
import org.zuel.crm.workbench.domain.ActivityRemark;
import org.zuel.crm.workbench.service.ActivityRemarkService;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 处理市场活动的备注,最好一个资源目录对应一个Controller，但是太长的话，可以根据表和功能再重新建一个Controller
 */
@Controller
public class ActivityRemarkController {

    @Autowired
    private ActivityRemarkService activityReMarkService;


    /**
     * 保存市场活动备注
     * @param remark 备注
     * @param session 会话
     * @return 响应
     */
    @ResponseBody
    @RequestMapping("/workbench/activity/saveCreateActivityRemark.do")
    public Object saveCreateActivityRemark(ActivityRemark remark, HttpSession session){
        ReturnObject returnObject = new ReturnObject();
        //封装注入参数
        remark.setId(UUIDUtils.getUUID());
        remark.setCreateTime(DateUtils.formateDateTime(new Date()));
        remark.setCreateBy(((User)session.getAttribute(Constants.SESSION_USER)).getId());
        remark.setEditFlag(Constants.REMARK_EDIT_FLAG_NO_EDITED);
        //保存
        try{
            int ret = activityReMarkService.saveCreateActivityRemark(remark);
            if (ret > 0){
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setRetData(remark);
            }else {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试...");
            }
        }catch (Exception e){
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试...");
        }
        return returnObject;
    }


    /**
     * 通过id删除市场活动备注
     * @param id 备注主键
     * @return 响应信息
     */
    @ResponseBody
    @RequestMapping("/workbench/activity/deleteActivityRemarkById.do")
    public ReturnObject deleteActivityRemarkById(String id){
        ReturnObject returnObject = new ReturnObject();
        try{
            int ret = activityReMarkService.deleteActivityRemarkById(id);
            if (ret > 0){
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试...");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试...");
        }
        return returnObject;
    }


    /**
     * 保存修改的市场活动备注
     * @param remark 备注
     * @param session 会话
     * @return 响应
     */
    @ResponseBody
    @RequestMapping("/workbench/activity/saveEditActivityRemark.do")
    public Object saveEditActivityRemark(ActivityRemark remark, HttpSession session){
        ReturnObject returnObject = new ReturnObject();
        //封装注入参数
        remark.setEditTime(DateUtils.formateDateTime(new Date()));
        remark.setEditBy(((User)session.getAttribute(Constants.SESSION_USER)).getId());
        remark.setEditFlag(Constants.REMARK_EDIT_FLAG_YES_EDITED);
        //修改
        try{
            int ret = activityReMarkService.updateActivityRemark(remark);
            if (ret > 0){
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
                //可以在前端获取，存在很小误差，下次登录会更新
                returnObject.setRetData(remark);
            }else {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试...");
            }
        }catch (Exception e){
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试...");
        }
        return returnObject;
    }
}
