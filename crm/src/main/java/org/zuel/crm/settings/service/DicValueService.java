package org.zuel.crm.settings.service;

import org.zuel.crm.settings.domain.DicValue;

import java.util.List;

/**
 * 数据字典值
 */
public interface DicValueService {

    /**
     * 根据数据字典类型查询数据字典值列表
     * @param typeCode
     * @return
     */
    List<DicValue> queryDicValueByTypeCode(String typeCode);
}
