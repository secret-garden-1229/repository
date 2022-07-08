package org.zuel.crm.workbench.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuel.crm.workbench.domain.Activity;
import org.zuel.crm.workbench.mapper.ActivityMapper;
import org.zuel.crm.workbench.service.ActivityService;

import java.util.List;
import java.util.Map;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    /**
     * 保存市场活动
     * @param activity 活动的封装
     * @return 影响数据库的条数
     */
    @Override
    public int saveCreateActivity(Activity activity) {
        return activityMapper.insertActivity(activity);
    }


    /**
     * 根据条件查询市场活动
     * @param map
     * @return
     */
    @Override
    public List<Activity> queryActivityByConditionForPage(Map<String, Object> map) {
        return activityMapper.selectActivityByConditionForPage(map);
    }

    /**
     * 根据条件查询市场活动总条数
     * @param map
     * @return
     */
    @Override
    public int queryCountOfActivityByCondition(Map<String, Object> map) {
        return activityMapper.selectCountOfActivityByCondition(map);
    }

    /**
     * 批量删除市场活动
     * @param ids
     * @return
     */
    @Override
    public int deleteActivityByIds(String[] ids) {
        return activityMapper.deleteActivityByIds(ids);
    }

    /**
     * 根据id查询市场活动
     * @param id
     * @return
     */
    @Override
    public Activity queryActivityById(String id) {
        return activityMapper.selectActivityById(id);
    }


    /**
     * 保存修改的市场活动对象
     * @param activity
     * @return
     */
    @Override
    public int saveEditActivity(Activity activity) {
        return activityMapper.updateActivity(activity);
    }


    /**
     * 查询所有的市场活动
     * @return
     */
    @Override
    public List<Activity> queryAllActivities() {
        return activityMapper.selectAllActivities();
    }


    /**
     * 根据id查询选择的市场活动
     * @return
     */
    @Override
    public List<Activity> queryActivitiesByIds(String[] ids) {
        return activityMapper.selectActivitiesByIds(ids);
    }

    /**
     * 批量添加市场活动
     * @param activityList
     * @return
     */
    @Override
    public int saveCreateActivityByList(List<Activity> activityList) {
        return activityMapper.insertActivitiesByList(activityList);
    }

    /**
     * 根据市场活动id查询市场活动的详细详细
     * @param id
     * @return
     */
    @Override
    public Activity queryActivityForDetailById(String id) {
        return activityMapper.selectActivityForDetailById(id);
    }


    /**
     * 根据线索id查询显示市场活动列表
     * @param clueId
     * @return
     */
    @Override
    public List<Activity> queryActivityForDetailByClueId(String clueId) {
        return activityMapper.selectActivityForDetailByClueId(clueId);
    }

    /**
     * 据name模糊查询id，把clueId关联的市场活动排除
     * @param map
     * @return
     */
    @Override
    public List<Activity> queryActivityForDetailByNameClueId(Map<String, Object> map) {
        return activityMapper.selectActivityForDetailByNameClueId(map);
    }

    /**
     * 根据市场活动id查询显示市场活动列表
     * @param ids
     * @return
     */
    @Override
    public List<Activity> queryActivityForDetailByIds(String[] ids) {
        return activityMapper.selectActivityForDetailByIds(ids);
    }


    /**
     * 根据ClueId查询，市场活动名称模糊查询
     * @param map
     * @return
     */
    @Override
    public List<Activity> queryActivityForConVertByNameClueId(Map<String, Object> map) {
        return activityMapper.selectActivityForConVertByNameClueId(map);
    }


}
