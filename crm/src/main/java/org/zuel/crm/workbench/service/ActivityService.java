package org.zuel.crm.workbench.service;

import org.zuel.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

/**
 * 市场活动业务层接口
 */
public interface ActivityService {

    /**
     * 保存市场活动
     * @param activity 活动的封装
     * @return 影响数据库的条数
     */
    int saveCreateActivity(Activity activity);

    /**
     * 根据条件查询市场活动分页
     * @param map
     * @return
     */
    List<Activity> queryActivityByConditionForPage(Map<String,Object> map);

    /**
     * 根据条件查询市场活动总条数
     * @param map
     * @return
     */
    int queryCountOfActivityByCondition(Map<String,Object> map);

    /**
     * 根据id批量删除市场活动
     * @param ids
     * @return
     */
    int deleteActivityByIds(String [] ids);

    /**
     * 根据id查询市场活动
     * @param id
     * @return
     */
    Activity queryActivityById(String id);

    /**
     * 保存修改的市场活动对象
     * @param activity
     * @return
     */
    int saveEditActivity(Activity activity);

    /**
     * 查询所有的市场活动
     * @return
     */
    List<Activity> queryAllActivities();

    /**
     * 根据id查询选择的市场活动
     * @return
     */
    List<Activity> queryActivitiesByIds(String[] ids);

    /**
     * 批量添加市场活动
     * @param activityList
     * @return
     */
    int saveCreateActivityByList(List<Activity> activityList);


    /**
     * 根据市场活动id查询市场活动的详细详细
     * @param id
     * @return
     */
    Activity queryActivityForDetailById(String id);


    /**
     * 根据线索id查询显示市场活动列表
     * @param clueId
     * @return
     */
    List<Activity> queryActivityForDetailByClueId(String clueId);

    /**
     * 据name模糊查询id，把clueId关联的市场活动排除
     * @param map
     * @return
     */
    List<Activity> queryActivityForDetailByNameClueId(Map<String,Object> map);

    /**
     * 根据市场活动id查询显示市场活动列表
     * @param ids
     * @return
     */
    List<Activity> queryActivityForDetailByIds(String[] ids);

    /**
     * 根据ClueId查询，市场活动名称模糊查询
     * @param map
     * @return
     */
    List<Activity> queryActivityForConVertByNameClueId(Map<String ,Object> map);
}
