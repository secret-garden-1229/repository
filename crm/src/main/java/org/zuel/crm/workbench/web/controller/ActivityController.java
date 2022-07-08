package org.zuel.crm.workbench.web.controller;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zuel.crm.commons.constants.Constants;
import org.zuel.crm.commons.domain.ReturnObject;
import org.zuel.crm.commons.utils.DateUtils;
import org.zuel.crm.commons.utils.HSSFUtils;
import org.zuel.crm.commons.utils.UUIDUtils;
import org.zuel.crm.settings.domain.User;
import org.zuel.crm.settings.service.UserService;
import org.zuel.crm.workbench.domain.Activity;
import org.zuel.crm.workbench.domain.ActivityRemark;
import org.zuel.crm.workbench.service.ActivityRemarkService;
import org.zuel.crm.workbench.service.ActivityService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 市场活动
 */
@Controller
public class ActivityController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityRemarkService activityReMarkService;


    /**
     * 市场活动的首页
     *
     * @param request 请求
     * @return 市场活动主页的视图
     */
    @RequestMapping("/workbench/activity/index.do")
    public String index(HttpServletRequest request) {
        //查询所有用户
        List<User> userList = userService.queryAllUsers();
        //保存
        request.setAttribute("userList", userList);
        //请求转发到市场活动
        return "workbench/activity/index";
    }


    /**
     * 处理保存市场活动
     *
     * @return 保存市场活动的结果
     */
    @RequestMapping("/workbench/activity/saveCreateActivity.do")
    @ResponseBody
    public Object saveCreateActivity(Activity activity, HttpServletRequest request) {
        //实体类中有位传入的参数，需要自己生成
        activity.setId(UUIDUtils.getUUID());
        activity.setCreateTime(DateUtils.formateDateTime(new Date()));
        //被谁创建的，从session中取出user的id
        activity.setCreateBy(((User) request.getSession().getAttribute(Constants.SESSION_USER)).getId());

        ReturnObject returnObject = new ReturnObject();
        try {
            //save
            int ret = activityService.saveCreateActivity(activity);
            if (ret > 0) {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试...");
        }
        return returnObject;
    }

    /**
     * 分页查询
     *
     * @return 分页结果
     */
    @ResponseBody
    @RequestMapping("/workbench/activity/queryActivityByConditionForPage.do")
    public Object queryActivityByConditionForPage(String name, String owner,
                                                  String startDate, String endDate,
                                                  int pageNo, int pageSize) {
        //封装参数
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("owner", owner);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("beginNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);

        //查询
        List<Activity> activityList = activityService.queryActivityByConditionForPage(map);
        int totalRows = activityService.queryCountOfActivityByCondition(map);

        //响应信息 java对象转json    -》 map 实体类 基本类型 list 数组
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("activityList", activityList);
        retMap.put("totalRows", totalRows);

        return retMap;
    }


    /**
     * 根据id批量删除
     * @param id
     * @return
     */
    @RequestMapping("/workbench/activity/deleteActivityIds.do")
    @ResponseBody
    public Object deleteActivityIds(String[] id) {
        ReturnObject returnObject = new ReturnObject();
        try {
            int ret = activityService.deleteActivityByIds(id);
            if (ret == id.length) {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试...");
        }
        return returnObject;
    }


    /**
     * 根据id查询市场活动
     *
     * @param id 市场活动id
     * @return 市场活动
     */
    @RequestMapping("/workbench/activity/queryActivityById.do")
    @ResponseBody
    public Object queryActivityById(String id) {
        return activityService.queryActivityById(id);
    }


    /**
     * 处理保存更新市场活动
     *
     * @return 市场活动修改状态
     */
    @RequestMapping("/workbench/activity/updateEditActivity.do")
    @ResponseBody
    public Object updateEditActivity(Activity activity, HttpServletRequest request) {
        //被谁修改的，从session中取出user的id
        activity.setEditBy(((User) request.getSession().getAttribute(Constants.SESSION_USER)).getId());
        activity.setEditTime(DateUtils.formateDateTime(new Date()));

        ReturnObject returnObject = new ReturnObject();
        try {
            //update
            int ret = activityService.saveEditActivity(activity);
            if (ret > 0) {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试...");
        }
        return returnObject;
    }


    /**
     * 导出文件
     * 由于返回的是文件，同步请求的页面不会刷新，但是会弹出下载的窗口
     * @param response 响应对象 ，void用字节流回传，并且设置响应头。 mvc传文件复杂，
     */
    @RequestMapping("/workbench/activity/exportAllActivities.do")
    public void exportAllActivities(HttpServletResponse response) {
        List<Activity> activityList = activityService.queryAllActivities();
        //创建excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("市场活动表");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        {
            cell.setCellValue("ID");
            cell = row.createCell(1);
            cell.setCellValue("所有者");
            cell = row.createCell(2);
            cell.setCellValue("名称");
            cell = row.createCell(3);
            cell.setCellValue("开始时间");
            cell = row.createCell(4);
            cell.setCellValue("结束时间");
            cell = row.createCell(5);
            cell.setCellValue("成本");
            cell = row.createCell(6);
            cell.setCellValue("描述");
            cell = row.createCell(7);
            cell.setCellValue("创建时间");
            cell = row.createCell(8);
            cell.setCellValue("创建者");
            cell = row.createCell(9);
            cell.setCellValue("修改时间");
            cell = row.createCell(10);
            cell.setCellValue("修改者");
        }
        //遍历list，创建HSSFRow，生成数据行
        Activity activity = null;
        if (activityList != null && activityList.size() > 0) {
            for (int i = 0; i < activityList.size(); ++i) {
                activity = activityList.get(i);
                //生成行
                row = sheet.createRow(i + 1);
                cell = row.createCell(0);
                cell.setCellValue(activity.getId());
                cell = row.createCell(1);
                cell.setCellValue(activity.getOwner());
                cell = row.createCell(2);
                cell.setCellValue(activity.getName());
                cell = row.createCell(3);
                cell.setCellValue(activity.getStartDate());
                cell = row.createCell(4);
                cell.setCellValue(activity.getEndDate());
                cell = row.createCell(5);
                cell.setCellValue(activity.getCost());
                cell = row.createCell(6);
                cell.setCellValue(activity.getDescription());
                cell = row.createCell(7);
                cell.setCellValue(activity.getCreateTime());
                cell = row.createCell(8);
                cell.setCellValue(activity.getCreateBy());
                cell = row.createCell(9);
                cell.setCellValue(activity.getEditTime());
                cell = row.createCell(10);
                cell.setCellValue(activity.getEditBy());
            }
        }
       /* //生成excel
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("E:\\Study_relation\\CRM_SSM\\workspace\\activities.xls");
            //write的响应效率低,直接可以把查询的对象从内存返回，不用保存到外存中
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        //下载
        response.setContentType("application/octet-stream;charset=UTF-8");
        //浏览器在接收到响应信息之后，默认情况下，直接在显示窗口中打开响应信息，即使打不开，也会调用应用程序打开，只用实在打不开，才会激活文件的保存
        //设置响应信息，使浏览器接收到响应信息之后，直接激活文件下载窗口， 即使可以打开也不打开
        response.addHeader("Content-Disposition", "attachment;filename=ActivityList.xls");
        ServletOutputStream out  = null;
        try {
            out = response.getOutputStream();
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //直接从内存写，不用下述方法，从内存到外存，再从外存到内存到网络传输
        //ActivityFileDownload(response);
    }

    /**
     * 下载文件
     * @param response
     * 效率较低
     public void ActivityFileDownload(HttpServletResponse response) {
        //返回文件(二进制流) 可以查http context-type 对照表
        response.setContentType("application/octet-stream;charset=UTF-8");
        //浏览器在接收到响应信息之后，默认情况下，直接在显示窗口中打开响应信息，即使打不开，也会调用应用程序打开，只用实在打不开，才会激活文件的保存
        //设置响应信息，使浏览器接收到响应信息之后，直接激活文件下载窗口， 即使可以打开也不打开
        response.addHeader("Content-Disposition", "attachment;filename=ActivityList.xls");

        ServletOutputStream out = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("E:\\Study_relation\\CRM_SSM\\workspace\\activities.xls");
            out = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                //谁开启谁关闭，out让tomcat去关闭
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/

    /**
     * 选择导出文件
     * 由于返回的是文件，同步请求的页面不会刷新，但是会弹出下载的窗口
     * @param response 响应对象 ，void用字节流回传，并且设置响应头。 mvc传文件复杂，
     */
    @RequestMapping("/workbench/activity/exportActivitiesByIds.do")
    public void exportActivitiesByIds(String[] id,HttpServletResponse response) {
        List<Activity> activityList = activityService.queryActivitiesByIds(id);
        //创建excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("市场活动表");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        {
            cell.setCellValue("ID");
            cell = row.createCell(1);
            cell.setCellValue("所有者");
            cell = row.createCell(2);
            cell.setCellValue("名称");
            cell = row.createCell(3);
            cell.setCellValue("开始时间");
            cell = row.createCell(4);
            cell.setCellValue("结束时间");
            cell = row.createCell(5);
            cell.setCellValue("成本");
            cell = row.createCell(6);
            cell.setCellValue("描述");
            cell = row.createCell(7);
            cell.setCellValue("创建时间");
            cell = row.createCell(8);
            cell.setCellValue("创建者");
            cell = row.createCell(9);
            cell.setCellValue("修改时间");
            cell = row.createCell(10);
            cell.setCellValue("修改者");
        }
        //遍历list，创建HSSFRow，生成数据行
        Activity activity = null;
        if (activityList != null && activityList.size() > 0) {
            for (int i = 0; i < activityList.size(); ++i) {
                activity = activityList.get(i);
                //生成行
                row = sheet.createRow(i + 1);
                cell = row.createCell(0);
                cell.setCellValue(activity.getId());
                cell = row.createCell(1);
                cell.setCellValue(activity.getOwner());
                cell = row.createCell(2);
                cell.setCellValue(activity.getName());
                cell = row.createCell(3);
                cell.setCellValue(activity.getStartDate());
                cell = row.createCell(4);
                cell.setCellValue(activity.getEndDate());
                cell = row.createCell(5);
                cell.setCellValue(activity.getCost());
                cell = row.createCell(6);
                cell.setCellValue(activity.getDescription());
                cell = row.createCell(7);
                cell.setCellValue(activity.getCreateTime());
                cell = row.createCell(8);
                cell.setCellValue(activity.getCreateBy());
                cell = row.createCell(9);
                cell.setCellValue(activity.getEditTime());
                cell = row.createCell(10);
                cell.setCellValue(activity.getEditBy());
            }
        }
        //下载
        response.setContentType("application/octet-stream;charset=UTF-8");
        //浏览器在接收到响应信息之后，默认情况下，直接在显示窗口中打开响应信息，即使打不开，也会调用应用程序打开，只用实在打不开，才会激活文件的保存
        //设置响应信息，使浏览器接收到响应信息之后，直接激活文件下载窗口， 即使可以打开也不打开
        response.addHeader("Content-Disposition", "attachment;filename=ActivityList.xls");
        ServletOutputStream out  = null;
        try {
            out = response.getOutputStream();
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 导入文件,文件上传需要配置文件上传解析器
     * @param activityFile 响应对象
     */
    @RequestMapping("/workbench/activity/importActivitiesByList.do")
    @ResponseBody
    public ReturnObject importActivitiesByList(MultipartFile activityFile,HttpServletRequest request) {
        ReturnObject returnObject = new ReturnObject();
        try{
            InputStream is = activityFile.getInputStream();
            HSSFWorkbook workbook = new HSSFWorkbook(is);
            HSSFSheet sheet = workbook.getSheetAt(0);
            HSSFRow row = null;
            HSSFCell cell = null;
            Activity activity = null;
            List<Activity> activityList = new ArrayList<>();
            //解析xls数据到 List中，首行是字段名称，直接从第一行开始，不从第零行开始
            for (int i = 1;i<=sheet.getLastRowNum();++i){
                row = sheet.getRow(i);//
                activity = new Activity();
                //实体类中有位传入的参数，需要自己生成
                activity.setId(UUIDUtils.getUUID());
                //bug：owner要使用ID，不是名字，理应插入时，要查询，这里一下代替
                activity.setOwner(((User) request.getSession().getAttribute(Constants.SESSION_USER)).getId());
                activity.setCreateTime(DateUtils.formateDateTime(new Date()));
                //被谁创建的，从session中取出user的id
                activity.setCreateBy(((User) request.getSession().getAttribute(Constants.SESSION_USER)).getId());
                for (int j= 0;j<row.getLastCellNum();++j){
                    cell = row.getCell(j);
                    //取列中的数据
                    String cellValue = HSSFUtils.getCellValueForStr(cell);
                    switch (j){
                        case 0:activity.setName(cellValue);break;
                        case 1:activity.setStartDate(cellValue);break;
                        case 2:activity.setEndDate(cellValue);break;
                        case 3:activity.setEndDate(cellValue);break;
                        case 4:activity.setDescription(cellValue);
                    }
                }
                activityList.add(activity);
            }
            int ret = activityService.saveCreateActivityByList(activityList);
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            //在后端代码中尽量少拼接，返回给前端拼接
            returnObject.setRetData(ret);
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试...");
        }
        return returnObject;
    }


    /**
     * 根据市场活动id查询市场活动的评论
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/workbench/activity/detailActivity.do")
    public String detailActivity(String id,HttpServletRequest request){
        Activity activity = activityService.queryActivityForDetailById(id);
        List<ActivityRemark> remarkList = activityReMarkService.queryActivityRemarkForDetailByActivityId(id);
        request.setAttribute("activity",activity);
        request.setAttribute("remarkList",remarkList);
        return "workbench/activity/detail";
    }



}
