package org.zuel.crm.workbench.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuel.crm.commons.constants.Constants;
import org.zuel.crm.commons.utils.DateUtils;
import org.zuel.crm.commons.utils.UUIDUtils;
import org.zuel.crm.settings.domain.User;
import org.zuel.crm.workbench.domain.Customer;
import org.zuel.crm.workbench.domain.FunnelVO;
import org.zuel.crm.workbench.domain.Tran;
import org.zuel.crm.workbench.domain.TranHistory;
import org.zuel.crm.workbench.mapper.CustomerMapper;
import org.zuel.crm.workbench.mapper.TranHistoryMapper;
import org.zuel.crm.workbench.mapper.TranMapper;
import org.zuel.crm.workbench.service.TranService;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("tranService")
public class TranServiceImpl implements TranService {

    @Autowired
    private TranMapper tranMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private TranHistoryMapper tranHistoryMapper;

    /**
     * 保存新创建的交易
     * @param map
     */
    @Override
    public void saveCreateTran(Map<String, Object> map) {
        //根据客户名称精确查询客户
        String customerName = (String) map.get("customerName");
        User user = (User) map.get(Constants.SESSION_USER);
        Customer customer = customerMapper.selectCustomerByName(customerName);
        if (customer == null){
            //客户不存在，新建客户
            customer = new Customer();
            customer.setName(customerName);
            customer.setOwner(user.getId());
            customer.setId(UUIDUtils.getUUID());
            customer.setCreateTime(DateUtils.formateDateTime(new Date()));
            customer.setCreateBy(user.getId());
            customerMapper.insertCustomer(customer);
        }
        //保存交易
        Tran tran = new Tran();
        tran.setStage((String) map.get("stage"));
        tran.setOwner((String) map.get("owner"));
        tran.setNextContactTime((String) map.get("nextContactTime"));
        tran.setName((String) map.get("name"));
        tran.setMoney((String) map.get("money"));
        tran.setId(UUIDUtils.getUUID());
        tran.setExpectedDate((String) map.get("expectedDate"));
        tran.setCustomerId(customer.getId());
        tran.setCreateTime(DateUtils.formateDateTime(new Date()));
        tran.setCreateBy(user.getId());
        tran.setContactSummary((String) map.get("contactSummary"));
        tran.setContactsId((String) map.get("contactsId"));
        tran.setActivityId((String) map.get("activityId"));
        tran.setDescription((String) map.get("description"));
        tran.setSource((String) map.get("source"));
        tran.setType((String) map.get("type"));
        tranMapper.insertTran(tran);
        //保存交易的历史
        TranHistory tranHistory =new TranHistory();
        tranHistory.setId(UUIDUtils.getUUID());
        tranHistory.setCreateBy(user.getId());
        tranHistory.setCreateTime(DateUtils.formateDateTime(new Date()));
        tranHistory.setExpectedDate(tran.getExpectedDate());
        tranHistory.setMoney(tran.getMoney());
        tranHistory.setTranId(tran.getId());
        tranHistory.setStage(tran.getStage());
        tranHistoryMapper.insertTranHistory(tranHistory);
    }


    /**
     * 查询明细
     * @param id
     * @return
     */
    @Override
    public Tran queryTranForDetailById(String id) {
        return tranMapper.selectTranForDetailById(id);
    }


    /**
     * 以组为单位查询 交易表中各个阶段的 数量
     * @return
     */
    @Override
    public List<FunnelVO> queryCountOfTranGroupByStage() {
        return tranMapper.selectCountOfTranGroupByStage();
    }


}
