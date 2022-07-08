package org.zuel.crm.workbench.service;

import java.util.List;

public interface CustomerService {

    /**
     * 查询所有的客户的名称
     * @return
     */
    List<String> queryAllCustomerName();


    /**
     * 模糊查询客户的名称
     * @return
     */
    List<String> queryCustomerNameByName(String name);
}
