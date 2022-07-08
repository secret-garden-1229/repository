package org.zuel.crm.workbench.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuel.crm.workbench.mapper.CustomerMapper;
import org.zuel.crm.workbench.service.CustomerService;

import java.util.List;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;


    /**
     * 查询所有的客户的名称
     * @return
     */
    @Override
    public List<String> queryAllCustomerName() {
        return customerMapper.selectAllCustomerName();
    }


    /**
     * 模糊查询客户的名称
     * @return
     */
    @Override
    public List<String> queryCustomerNameByName(String name) {
        return customerMapper.selectCustomerNameByName(name);
    }
}
