package com.cn.oa.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName depart
 */
@Data
public class Depart implements Serializable {
    /**
     * 
     */
    private Integer did;

    /**
     * 
     */
    private String dname;

    /**
     * 
     */
    private String duty;

    /**
     * 
     */
    private Date credate;

    /**
     * 
     */
    private Integer dstatus;

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Depart other = (Depart) that;
        return (this.getDid() == null ? other.getDid() == null : this.getDid().equals(other.getDid()))
            && (this.getDname() == null ? other.getDname() == null : this.getDname().equals(other.getDname()))
            && (this.getDuty() == null ? other.getDuty() == null : this.getDuty().equals(other.getDuty()))
            && (this.getCredate() == null ? other.getCredate() == null : this.getCredate().equals(other.getCredate()))
            && (this.getDstatus() == null ? other.getDstatus() == null : this.getDstatus().equals(other.getDstatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDid() == null) ? 0 : getDid().hashCode());
        result = prime * result + ((getDname() == null) ? 0 : getDname().hashCode());
        result = prime * result + ((getDuty() == null) ? 0 : getDuty().hashCode());
        result = prime * result + ((getCredate() == null) ? 0 : getCredate().hashCode());
        result = prime * result + ((getDstatus() == null) ? 0 : getDstatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", did=").append(did);
        sb.append(", dname=").append(dname);
        sb.append(", duty=").append(duty);
        sb.append(", credate=").append(credate);
        sb.append(", dstatus=").append(dstatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}