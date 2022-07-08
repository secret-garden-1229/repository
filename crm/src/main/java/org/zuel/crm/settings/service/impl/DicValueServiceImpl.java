package org.zuel.crm.settings.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuel.crm.settings.domain.DicValue;
import org.zuel.crm.settings.mapper.DicValueMapper;
import org.zuel.crm.settings.service.DicValueService;

import java.util.List;

@Service("dicValueService")
public class DicValueServiceImpl implements DicValueService {

    @Autowired
    private DicValueMapper dicValueMapper;

    /**
     * 根据数据字典类型查询数据字典值列表
     * @param typeCode
     * @return
     */
    @Override
    public List<DicValue> queryDicValueByTypeCode(String typeCode) {
        return dicValueMapper.selectDicTypeByTypeCode(typeCode);
    }
}
