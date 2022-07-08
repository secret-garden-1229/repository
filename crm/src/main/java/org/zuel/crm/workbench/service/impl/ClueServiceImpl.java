package org.zuel.crm.workbench.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuel.crm.commons.utils.DateUtils;
import org.zuel.crm.commons.utils.UUIDUtils;
import org.zuel.crm.settings.domain.User;
import org.zuel.crm.workbench.domain.*;
import org.zuel.crm.workbench.mapper.*;
import org.zuel.crm.workbench.service.ClueService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("clueService")
public class ClueServiceImpl implements ClueService {

    @Autowired
    private ClueMapper clueMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private ContactsMapper contactsMapper;

    @Autowired
    private ClueRemarkMapper clueRemarkMapper;

    @Autowired
    private CustomerRemarkMapper customerRemarkMapper;

    @Autowired
    private ContactsRemarkMapper contactsRemarkMapper;

    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;

    @Autowired
    private ContactsActivityRelationMapper contactsActivityRelationMapper;

    @Autowired
    private TranMapper tranMapper;

    @Autowired
    private TranRemarkMapper tranRemarkMapper;

    /**
     * 保存 创建的 线索
     * @param clue
     * @return
     */
    @Override
    public int saveCreateClue(Clue clue) {
        return clueMapper.insertClue(clue);
    }

    /**
     * 根据条件查询线索分页
     * @param map
     * @return
     */
    @Override
    public List<Clue> queryClueByConditionForPage(Map<String, Object> map) {
        return clueMapper.selectClueByConditionForPage(map);
    }

    /**
     * 根据条件查询线索总条数
     * @param map
     * @return
     */
    @Override
    public int queryCountOfClueByCondition(Map<String, Object> map) {
        return clueMapper.selectCountOfActivityByCondition(map);
    }

    /**
     * 根据id查询线索明细
     * @param id
     * @return
     */
    @Override
    public Clue queryClueForDetailById(String id) {
        return clueMapper.selectClueForDetailById(id);
    }


    /**
     * 处理线索转换
     * @param map
     */
    @Override
    public void saveClueConvert(Map<String, Object> map) {
        String clueId = (String) map.get("clueId");
        //当前转换线索的用户，不确定的字段（所有者等），填当前用户的信息
        User user = (User) map.get("user");
        //查询出的线索的源信息  把线索中的公司信息转换到客户表中
        Clue clue = clueMapper.selectClueById(clueId);
        Customer customer =new Customer();
        customer.setAddress(clue.getAddress());
        customer.setCreateBy(user.getId());//谁转换线索的，谁就是创建者，在service和mapper层不要引入web的session的技术
        customer.setCreateTime(DateUtils.formateDateTime(new Date()));
        customer.setContactSummary(clue.getContactSummary());
        customer.setDescription(clue.getDescription());
        customer.setName(clue.getCompany());
        customer.setNextContactTime(clue.getNextContactTime());
        customer.setOwner(user.getId());
        customer.setId(UUIDUtils.getUUID());
        customer.setPhone(clue.getPhone());
        customer.setWebsite(clue.getWebsite());
        //save customer
        customerMapper.insertCustomer(customer);
        //to contacts 转到联系人
        Contacts contacts = new Contacts();
        contacts.setAddress(clue.getAddress());
        contacts.setContactSummary(clue.getContactSummary());
        contacts.setAppellation(clue.getAppellation());
        contacts.setCreateBy(user.getId());
        contacts.setCreateTime(DateUtils.formateDateTime(new Date()));
        contacts.setNextContactTime(clue.getNextContactTime());
        contacts.setDescription(clue.getDescription());
        contacts.setCustomerId(customer.getId());//公司id在上面转换时已经生成
        contacts.setId(UUIDUtils.getUUID());
        contacts.setEmail(clue.getEmail());
        contacts.setMphone(clue.getMphone());
        contacts.setOwner(user.getId());
        contacts.setFullname(clue.getFullname());
        contacts.setJob(clue.getJob());
        contacts.setSource(clue.getSource());
        contactsMapper.insertContacts(contacts);

        //将线索 转 客户公海, 未做

        //线索备注 转换
        List<ClueRemark> clueRemarkList = clueRemarkMapper.selectClueRemarkByClueId(clueId);
        if (clueRemarkList!=null && clueRemarkList.size()>0){
            List<CustomerRemark> customerRemarkList = new ArrayList<>();
            List<ContactsRemark> contactsRemarkList = new ArrayList<>();
            CustomerRemark customerRemark = null;
            ContactsRemark contactsRemark = null;
            for (ClueRemark clueRemark:clueRemarkList){
                customerRemark = new CustomerRemark();
                customerRemark.setCreateBy(clueRemark.getCreateBy());
                customerRemark.setCreateTime(clueRemark.getCreateTime());
                customerRemark.setEditBy(clueRemark.getEditBy());
                customerRemark.setEditTime(clueRemark.getEditTime());
                customerRemark.setEditFlag(clueRemark.getEditFlag());
                customerRemark.setId(UUIDUtils.getUUID());
                customerRemark.setNoteContent(clueRemark.getNoteContent());
                customerRemark.setCustomerId(customer.getId());
                customerRemarkList.add(customerRemark);
                //
                contactsRemark = new ContactsRemark();
                contactsRemark.setId(UUIDUtils.getUUID());
                contactsRemark.setCreateBy(clueRemark.getCreateBy());
                contactsRemark.setCreateTime(clueRemark.getCreateTime());
                contactsRemark.setEditFlag(clueRemark.getEditFlag());
                contactsRemark.setEditBy(clueRemark.getEditBy());
                contactsRemark.setEditTime(clueRemark.getEditTime());
                contactsRemark.setContactsId(contacts.getId());
                contactsRemark.setNoteContent(clueRemark.getNoteContent());
                contactsRemarkList.add(contactsRemark);
            }
            customerRemarkMapper.insertCustomerRemarkByList(customerRemarkList);
            contactsRemarkMapper.insertContactsRemarkByList(contactsRemarkList);
        }

        //转移关联关系 线索-市场活动 -》 联系人-市场活动
        List<ClueActivityRelation> carList = clueActivityRelationMapper.selectClueActivityRelationByClueId(clueId);
        if (carList !=null && carList.size()>0){
            List<ContactsActivityRelation> coarList = new ArrayList<>();
            ContactsActivityRelation coar =  null;
            for (ClueActivityRelation car:carList){
                coar =new ContactsActivityRelation();
                coar.setActivityId(car.getActivityId());
                coar.setContactsId(contacts.getId());
                coar.setId(UUIDUtils.getUUID());
                coarList.add(coar);
            }
            contactsActivityRelationMapper.insertContactsActivityRelationByList(coarList);
        }

        //转换交易表
        if ("true".equals((String)map.get("isCreateTran"))){
            Tran tran = new Tran();
            tran.setActivityId((String) map.get("activityId"));
            tran.setContactsId(contacts.getId());
            tran.setCreateBy(user.getId());
            tran.setCreateTime(DateUtils.formateDateTime(new Date()));
            tran.setCustomerId(customer.getId());
            tran.setExpectedDate((String) map.get("expectedDate"));
            tran.setId(UUIDUtils.getUUID());
            tran.setMoney((String) map.get("money"));
            tran.setName((String) map.get("name"));
            tran.setStage((String) map.get("stage"));
            tran.setOwner(user.getId());
            tranMapper.insertTran(tran);
            //转换交易备注
            if (clueRemarkList!=null && clueRemarkList.size()>0){
                TranRemark tranRemark = null;
                List<TranRemark> tranRemarkList = new ArrayList<>();
                for (ClueRemark clueRemark:clueRemarkList){
                    tranRemark.setTranId(tran.getId());
                    tranRemark.setCreateBy(clueRemark.getCreateBy());
                    tranRemark.setCreateTime(clueRemark.getCreateTime());
                    tranRemark.setEditFlag(clueRemark.getEditFlag());
                    tranRemark.setEditBy(clueRemark.getEditBy());
                    tranRemark.setEditTime(clueRemark.getEditTime());
                    tranRemark.setNoteContent(clueRemark.getNoteContent());
                    tranRemark.setId(UUIDUtils.getUUID());
                    tranRemarkList.add(tranRemark);
                }
                tranRemarkMapper.insertTranRemarkByList(tranRemarkList);
            }
        }

        //删除线索备注
        clueMapper.deleteClueByClueId(clueId);

        //删除线索和市场活动的关联
        clueActivityRelationMapper.deleteClueActivityRelationByClueId(clueId);

        //删除线索
        clueMapper.deleteClueByClueId(clueId);

    }


}
