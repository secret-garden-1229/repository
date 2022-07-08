package org.zuel.crm.workbench.service;

import org.zuel.crm.workbench.domain.FunnelVO;
import org.zuel.crm.workbench.domain.Tran;

import java.util.List;
import java.util.Map;

public interface TranService {

    /**
     * 保存新创建的交易
     * @param map
     */
    void saveCreateTran(Map<String,Object> map);

    /**
     * 查询明细
     * @param id
     * @return
     */
    Tran queryTranForDetailById(String id);

    /**
     * 以组为单位查询 交易表中各个阶段的 数量
     * @return
     */
    List<FunnelVO> queryCountOfTranGroupByStage();
}
