package org.zuel.crm.workbench.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuel.crm.workbench.domain.TranRemark;
import org.zuel.crm.workbench.mapper.TranRemarkMapper;
import org.zuel.crm.workbench.service.TranRemarkService;

import java.util.List;
@Service("tranRemarkService")
public class TranRemarkServiceImpl implements TranRemarkService {

    @Autowired
    private TranRemarkMapper tranRemarkMapper;

    /**
     * 根据交易id查询备注明细
     * @param tranId
     * @return
     */
    @Override
    public List<TranRemark> queryTranRemarkForDetailByTranId(String tranId) {
        return tranRemarkMapper.selectTranRemarkForDetailByTranId(tranId);
    }
}
