package org.zuel.crm.workbench.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuel.crm.workbench.domain.ClueActivityRelation;
import org.zuel.crm.workbench.mapper.ClueActivityRelationMapper;
import org.zuel.crm.workbench.service.ClueActivityRelationService;

import java.util.List;

@Service("clueActivityRelationService")
public class ClueActivityRelationServiceImpl implements ClueActivityRelationService {

    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;

    /**
     * 批量插入市场活动和线索的关系
     * @param list
     * @return
     */
    @Override
    public int saveCreateClueActivityRelationByList(List<ClueActivityRelation> list) {
        return clueActivityRelationMapper.insertClueActivityRelationByList(list);
    }


    /**
     * 根据 线索id 市场活动id 来  删除关联
     * @param relation
     * @return
     */
    @Override
    public int deleteClueActivityRelationByClueActivityId(ClueActivityRelation relation){
        return clueActivityRelationMapper.deleteClueActivityRelationByClueActivityId(relation);
    }
}
