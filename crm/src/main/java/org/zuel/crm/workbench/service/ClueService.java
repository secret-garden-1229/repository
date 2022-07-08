package org.zuel.crm.workbench.service;

import org.zuel.crm.workbench.domain.Clue;

import java.util.List;
import java.util.Map;

/**
 *  线索
 */
public interface ClueService {

    /**
     * 保存 创建的 线索
     * @param clue
     * @return
     */
    int saveCreateClue(Clue clue);

    /**
     * 根据条件查询线索分页
     * @param map
     * @return
     */
    List<Clue> queryClueByConditionForPage(Map<String,Object> map);

    /**
     * 根据条件查询线索总条数
     * @param map
     * @return
     */
    int queryCountOfClueByCondition(Map<String,Object> map);

    /**
     * 根据id查询线索明细
     * @param id
     * @return
     */
    Clue queryClueForDetailById(String id);

    /**
     * 处理线索转换
     * @param map
     */
    void saveClueConvert(Map<String,Object> map);
}
