package org.zuel.crm.workbench.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuel.crm.workbench.domain.ClueRemark;
import org.zuel.crm.workbench.mapper.ClueRemarkMapper;
import org.zuel.crm.workbench.service.ClueRemarkService;

import java.util.List;

@Service("clueRemarkService")
public class ClueRemarkServiceImpl implements ClueRemarkService {

    @Autowired
    private ClueRemarkMapper clueRemarkMapper;


    /**
     * 根据线索id查询备注列表
     * @param clueId
     * @return
     */
    @Override
    public List<ClueRemark> queryClueRemarkForDetailByClueId(String clueId) {
        return clueRemarkMapper.selectClueRemarkForDetailByClueId(clueId);
    }
}
