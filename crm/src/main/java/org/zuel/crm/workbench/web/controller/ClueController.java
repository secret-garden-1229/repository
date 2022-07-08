package org.zuel.crm.workbench.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.zuel.crm.commons.constants.Constants;
import org.zuel.crm.commons.domain.ReturnObject;
import org.zuel.crm.commons.utils.DateUtils;
import org.zuel.crm.commons.utils.UUIDUtils;
import org.zuel.crm.settings.domain.DicValue;
import org.zuel.crm.settings.domain.User;
import org.zuel.crm.settings.service.DicValueService;
import org.zuel.crm.settings.service.UserService;
import org.zuel.crm.workbench.domain.Activity;
import org.zuel.crm.workbench.domain.Clue;
import org.zuel.crm.workbench.domain.ClueActivityRelation;
import org.zuel.crm.workbench.domain.ClueRemark;
import org.zuel.crm.workbench.service.ActivityService;
import org.zuel.crm.workbench.service.ClueActivityRelationService;
import org.zuel.crm.workbench.service.ClueRemarkService;
import org.zuel.crm.workbench.service.ClueService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class ClueController {

    @Autowired
    private DicValueService dicValueService;

    @Autowired
    private UserService userService;

    @Autowired
    private ClueService clueService;

    @Autowired
    private ClueRemarkService clueRemarkService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ClueActivityRelationService clueActivityRelationService;

    @RequestMapping("/workbench/clue/index.do")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        //下拉列表中的用户
        List<User> userList = userService.queryAllUsers();
        //查询称呼类型的字典值
        List<DicValue> appellationList = dicValueService.queryDicValueByTypeCode("appellation");
        //线索状态类型的字典值
        List<DicValue> clueStateList = dicValueService.queryDicValueByTypeCode("clueState");
        //线索来源字典值
        List<DicValue> sourceList = dicValueService.queryDicValueByTypeCode("source");
        //把数据保存，跳转
        mav.addObject("userList", userList);
        mav.addObject("appellationList", appellationList);
        mav.addObject("clueStateList", clueStateList);
        mav.addObject("sourceList", sourceList);
        mav.setViewName("workbench/clue/index");
        return mav;
    }


    /**
     * 保存创建的线索
     *
     * @param clue
     * @param session
     * @return
     */
    @RequestMapping("/workbench/clue/saveCreateClue.do")
    public @ResponseBody
    Object saveCreateClue(Clue clue, HttpSession session) {
        ReturnObject returnObject = new ReturnObject();

        clue.setId(UUIDUtils.getUUID());
        clue.setCreateBy(((User) session.getAttribute(Constants.SESSION_USER)).getId());
        clue.setCreateTime(DateUtils.formateDateTime(new Date()));

        //
        try {
            int ret = clueService.saveCreateClue(clue);
            if (ret > 0) {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试...");
            }
        } catch (Exception e) {
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试...");
        }
        return returnObject;
    }


    /**
     * 分页查询
     *
     * @param fullname
     * @param owner
     * @param company
     * @param phone
     * @param mphone
     * @param source
     * @param state
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping("/workbench/clue/queryActivityByConditionForPage.do")
    public @ResponseBody
    Object queryClueByConditionForPage(String fullname, String owner,
                                       String company, String phone,
                                       String mphone, String source,
                                       String state, int pageNo, int pageSize) {
        //封装
        Map<String, Object> map = new HashMap<>();
        map.put("fullname", fullname);
        map.put("owner", owner);
        map.put("company", company);
        map.put("phone", phone);
        map.put("mphone", mphone);
        map.put("source", source);
        map.put("state", state);
        map.put("beginNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);

        //查询
        List<Clue> clueList = clueService.queryClueByConditionForPage(map);
        int totalRows = clueService.queryCountOfClueByCondition(map);

        //响应
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("clueList", clueList);
        retMap.put("totalRows", totalRows);
        return retMap;
    }


    /**
     * 根据线索id查询明细
     * @param id 线索id
     * @return
     */
    @RequestMapping("/workbench/clue/queryClueDetail.do")
    public ModelAndView queryClueDetail(String id) {
        ModelAndView mav = new ModelAndView();
        //查询数据
        Clue clue = clueService.queryClueForDetailById(id);
        List<ClueRemark> clueRemarkList = clueRemarkService.queryClueRemarkForDetailByClueId(id);
        List<Activity> activityList = activityService.queryActivityForDetailByClueId(id);
        //渲染传出
        mav.addObject("clue", clue);
        mav.addObject("clueRemarkList", clueRemarkList);
        mav.addObject("activityList", activityList);
        mav.setViewName("workbench/clue/detail");
        return mav;
    }

    /**
     * 据name模糊查询id，把clueId关联的市场活动排除
     *
     * @param activityName
     * @param clueId
     * @return
     */
    @RequestMapping("/workbench/clue/queryActivityForDetailByNameClueId.do")
    public @ResponseBody
    Object queryActivityForDetailByNameClueId(String activityName, String clueId) {
        Map<String, Object> map = new HashMap<>();
        map.put("activityName", activityName);
        map.put("clueId", clueId);
        //return
        List<Activity> activityList = activityService.queryActivityForDetailByNameClueId(map);
        return activityList;
    }

    /**
     * 绑定市场活动和线索
     *
     * @param activityId
     * @param clueId
     * @return
     */
    @RequestMapping("/workbench/clue/saveBund.do")
    public @ResponseBody
    Object saveBund(String[] activityId, String clueId) {
        ReturnObject returnObject = new ReturnObject();
        ClueActivityRelation car = null;
        List<ClueActivityRelation> list = new ArrayList<>();
        try {
            for (String aId : activityId) {
                car = new ClueActivityRelation();
                car.setClueId(clueId);
                car.setId(UUIDUtils.getUUID());
                car.setActivityId(aId);
                list.add(car);
            }
            int ret = clueActivityRelationService.saveCreateClueActivityRelationByList(list);
            if (ret == list.size()) {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
                List<Activity> activityList = activityService.queryActivityForDetailByIds(activityId);
                returnObject.setRetData(activityList);
            } else {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试...");
            }
        } catch (Exception e) {
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试...");
            e.printStackTrace();
        }
        return returnObject;
    }

    /**
     * 根据 线索id 市场活动id 来  删除关联
     * @param relation
     * @return
     */
    @RequestMapping("/workbench/clue/saveunBund.do")
    public @ResponseBody Object saveunBund(ClueActivityRelation relation){
        ReturnObject returnObject = new ReturnObject();
        try{
            int ret = clueActivityRelationService.deleteClueActivityRelationByClueActivityId(relation);
            if (ret>0){
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
     * 跳转到线索转换页面
     * @param id
     * @return
     */
    @RequestMapping("/workbench/clue/toConvert.do")
    public String toConvert(String id, HttpServletRequest request){
        Clue clue = clueService.queryClueForDetailById(id);
        List<DicValue> stageList = dicValueService.queryDicValueByTypeCode("stage");
        request.setAttribute("clue",clue);
        request.setAttribute("stageList",stageList);
        return "workbench/clue/convert";
    }


    /**
     * 根据ClueId查询，市场活动名称模糊查询
     * @param activityName
     * @param clueId
     * @return
     */
    @RequestMapping("/workbench/clue/queryActivityForConvertByNameClueId.do")
    public @ResponseBody Object queryActivityForConvertByNameClueId(String activityName,String clueId){
        //
        Map<String ,Object> map = new HashMap<>();
        map.put("activityName",activityName);
        map.put("clueId",clueId);
        //service
        List<Activity> activityList = activityService.queryActivityForConVertByNameClueId(map);
        return activityList;
    }

    @RequestMapping("/workbench/clue/convertClue.do")
    public @ResponseBody Object convertClue(String clueId,String isCreateTran,
                                            String money,String name,String stage,
                                            String activityId,String expectedDate,
                                            HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("clueId",clueId);
        map.put("isCreateTran",isCreateTran);
        map.put("money",money);
        map.put("name",name);
        map.put("stage",stage);
        map.put("activityId",activityId);
        map.put("expectedDate",expectedDate);
        map.put("user",request.getSession().getAttribute(Constants.SESSION_USER));
        ReturnObject returnObject = new ReturnObject();
        try{
            //service
            clueService.saveClueConvert(map);
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
        }catch (Exception e){
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试...");
        }
        return returnObject;
    }

}
