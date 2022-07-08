package org.zuel.crm.commons.domain;
//commons 公共包

/**
 * 返回给前端的数据封装类
 */
public class ReturnObject {

    /**
     * 处理成功或者失败的标记  0 失败  1 成功
     */
    private String code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 返回的其他属性
     */
    private Object retData;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getRetData() {
        return retData;
    }

    public void setRetData(Object retData) {
        this.retData = retData;
    }
}
