package org.zuel.crm.workbench.service;

import org.zuel.crm.workbench.domain.ActivityRemark;

import java.util.List;

/**
 * 市场活动备注业务层接口
 */
public interface ActivityRemarkService {

    /**
     * 根据市场活动id查询备注
     * @param id
     * @return
     */
    List<ActivityRemark> queryActivityRemarkForDetailByActivityId(String id);

    /**
     * 保存市场活动备注
     * @param remark
     * @return
     */
    int saveCreateActivityRemark(ActivityRemark remark);

    /**
     * 根据备注id来删除备注
     * @param id
     * @return
     */
    int deleteActivityRemarkById(String id);


    /**
     * 根据备注id来修改备注
     * @param remark
     * @return
     */
    int updateActivityRemark(ActivityRemark remark);
}
