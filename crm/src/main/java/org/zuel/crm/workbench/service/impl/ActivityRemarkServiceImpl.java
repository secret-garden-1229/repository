package org.zuel.crm.workbench.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuel.crm.workbench.domain.ActivityRemark;
import org.zuel.crm.workbench.mapper.ActivityRemarkMapper;
import org.zuel.crm.workbench.service.ActivityRemarkService;

import java.util.List;

@Service
public class ActivityRemarkServiceImpl implements ActivityRemarkService {

    @Autowired
    private ActivityRemarkMapper activityRemarkMapper;


    /**
     * 根据市场活动的id查询市场活动的评论
     * @param id
     * @return
     */
    @Override
    public List<ActivityRemark> queryActivityRemarkForDetailByActivityId(String id) {
        return activityRemarkMapper.selectActivityRemarkForDetailByActivityId(id);
    }

    /**
     * 保存市场活动备注
     * @param remark
     * @return
     */
    @Override
    public int saveCreateActivityRemark(ActivityRemark remark) {
        return activityRemarkMapper.insertActivityRemark(remark);
    }

    /**
     * 根据备注id来删除备注
     * @param id
     * @return
     */
    @Override
    public int deleteActivityRemarkById(String id) {
        return activityRemarkMapper.deleteActivityRemarkById(id);
    }


    /**
     * 根据备注id来修改备注
     * @param remark
     * @return
     */
    @Override
    public int updateActivityRemark(ActivityRemark remark) {
        return activityRemarkMapper.updateActivityRemark(remark);
    }
}
