package org.zuel.crm.workbench.service;

import org.springframework.stereotype.Service;
import org.zuel.crm.workbench.domain.TranHistory;

import java.util.List;


public interface TranHistoryService {

    /**
     * 根据交易id查询交易历史明细
     * @param tranId
     * @return
     */
    List<TranHistory> queryTranHistoryForDetailByTranId(String tranId);
}
