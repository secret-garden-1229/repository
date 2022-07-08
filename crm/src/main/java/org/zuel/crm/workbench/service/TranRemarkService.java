package org.zuel.crm.workbench.service;

import org.zuel.crm.workbench.domain.TranRemark;

import java.util.List;

public interface TranRemarkService {

    /**
     * 根据交易id查询备注明细
     * @param tranId
     * @return
     */
    List<TranRemark> queryTranRemarkForDetailByTranId(String tranId);
}
