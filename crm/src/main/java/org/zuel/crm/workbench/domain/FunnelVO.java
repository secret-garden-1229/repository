package org.zuel.crm.workbench.domain;

/**
 * 返回前端的 漏斗图的 实体
 */
public class FunnelVO {
    private String name;
    private int value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
