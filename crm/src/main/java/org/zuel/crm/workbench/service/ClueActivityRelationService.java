package org.zuel.crm.workbench.service;

import org.zuel.crm.workbench.domain.ClueActivityRelation;

import java.util.List;

public interface ClueActivityRelationService {

    /**
     * 批量插入市场活动和线索的关系
     * @param list
     * @return
     */
    int saveCreateClueActivityRelationByList(List<ClueActivityRelation> list);

    /**
     * 根据 线索id 市场活动id 来  删除关联
     * @param relation
     * @return
     */
    int deleteClueActivityRelationByClueActivityId(ClueActivityRelation relation);
}
