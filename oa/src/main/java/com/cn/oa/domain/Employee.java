package com.cn.oa.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName employee
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends Depart implements Serializable {
    /**
     * 
     */
    private Integer eid;

    /**
     * 
     */
    private String ename;

    /**
     * 
     */
    private String epass;

    /**
     * 
     */
    private String realname;

    /**
     * 
     */
    private Integer esex;

    /**
     * 
     */
    private Date entrydate;

    /**
     * 
     */
    private Date leavedate;

    /**
     * 
     */
    private Integer position;

    /**
     * 
     */
    private Integer sal;

    /**
     * 
     */
    private Integer estatus;

    /**
     * 
     */
    private Integer did;

    private String role;

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
        Employee other = (Employee) that;
        return (this.getEid() == null ? other.getEid() == null : this.getEid().equals(other.getEid()))
            && (this.getEname() == null ? other.getEname() == null : this.getEname().equals(other.getEname()))
            && (this.getEpass() == null ? other.getEpass() == null : this.getEpass().equals(other.getEpass()))
            && (this.getRealname() == null ? other.getRealname() == null : this.getRealname().equals(other.getRealname()))
            && (this.getEsex() == null ? other.getEsex() == null : this.getEsex().equals(other.getEsex()))
            && (this.getEntrydate() == null ? other.getEntrydate() == null : this.getEntrydate().equals(other.getEntrydate()))
            && (this.getLeavedate() == null ? other.getLeavedate() == null : this.getLeavedate().equals(other.getLeavedate()))
            && (this.getPosition() == null ? other.getPosition() == null : this.getPosition().equals(other.getPosition()))
            && (this.getSal() == null ? other.getSal() == null : this.getSal().equals(other.getSal()))
            && (this.getEstatus() == null ? other.getEstatus() == null : this.getEstatus().equals(other.getEstatus()))
            && (this.getDid() == null ? other.getDid() == null : this.getDid().equals(other.getDid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getEid() == null) ? 0 : getEid().hashCode());
        result = prime * result + ((getEname() == null) ? 0 : getEname().hashCode());
        result = prime * result + ((getEpass() == null) ? 0 : getEpass().hashCode());
        result = prime * result + ((getRealname() == null) ? 0 : getRealname().hashCode());
        result = prime * result + ((getEsex() == null) ? 0 : getEsex().hashCode());
        result = prime * result + ((getEntrydate() == null) ? 0 : getEntrydate().hashCode());
        result = prime * result + ((getLeavedate() == null) ? 0 : getLeavedate().hashCode());
        result = prime * result + ((getPosition() == null) ? 0 : getPosition().hashCode());
        result = prime * result + ((getSal() == null) ? 0 : getSal().hashCode());
        result = prime * result + ((getEstatus() == null) ? 0 : getEstatus().hashCode());
        result = prime * result + ((getDid() == null) ? 0 : getDid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", eid=").append(eid);
        sb.append(", ename=").append(ename);
        sb.append(", epass=").append(epass);
        sb.append(", realname=").append(realname);
        sb.append(", esex=").append(esex);
        sb.append(", entrydate=").append(entrydate);
        sb.append(", leavedate=").append(leavedate);
        sb.append(", position=").append(position);
        sb.append(", sal=").append(sal);
        sb.append(", estatus=").append(estatus);
        sb.append(", did=").append(did);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}