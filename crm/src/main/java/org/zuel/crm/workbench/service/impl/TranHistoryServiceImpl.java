package org.zuel.crm.workbench.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuel.crm.workbench.domain.TranHistory;
import org.zuel.crm.workbench.mapper.TranHistoryMapper;
import org.zuel.crm.workbench.service.TranHistoryService;

import java.util.List;
@Service("tranHistoryService")
public class TranHistoryServiceImpl implements TranHistoryService {

    @Autowired
    private TranHistoryMapper tranHistoryMapper;
    /**
     * 根据交易id查询交易历史明细
     * @param tranId
     * @return
     */
    @Override
    public List<TranHistory> queryTranHistoryForDetailByTranId(String tranId) {
        return tranHistoryMapper.selectTranHistoryFOrDetailByTranId(tranId);
    }
}
