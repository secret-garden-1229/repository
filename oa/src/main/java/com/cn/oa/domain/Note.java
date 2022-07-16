package com.cn.oa.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName note
 */
@Data
public class Note implements Serializable {
    /**
     * 
     */
    private Integer nid;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private String context;

    /**
     * 
     */
    private Date startdate;

    /**
     * 
     */
    private Date enddate;

    /**
     * 
     */
    private Double length;

    /**
     * 
     */
    private Date subdate;

    /**
     * 
     */
    private Integer estatus;

    /**
     * 
     */
    private Date reldate;

    /**
     * 
     */
    private Integer eid;

    private String realname;

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
        Note other = (Note) that;
        return (this.getNid() == null ? other.getNid() == null : this.getNid().equals(other.getNid()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getContext() == null ? other.getContext() == null : this.getContext().equals(other.getContext()))
            && (this.getStartdate() == null ? other.getStartdate() == null : this.getStartdate().equals(other.getStartdate()))
            && (this.getEnddate() == null ? other.getEnddate() == null : this.getEnddate().equals(other.getEnddate()))
            && (this.getLength() == null ? other.getLength() == null : this.getLength().equals(other.getLength()))
            && (this.getSubdate() == null ? other.getSubdate() == null : this.getSubdate().equals(other.getSubdate()))
            && (this.getEstatus() == null ? other.getEstatus() == null : this.getEstatus().equals(other.getEstatus()))
            && (this.getReldate() == null ? other.getReldate() == null : this.getReldate().equals(other.getReldate()))
            && (this.getEid() == null ? other.getEid() == null : this.getEid().equals(other.getEid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getNid() == null) ? 0 : getNid().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getContext() == null) ? 0 : getContext().hashCode());
        result = prime * result + ((getStartdate() == null) ? 0 : getStartdate().hashCode());
        result = prime * result + ((getEnddate() == null) ? 0 : getEnddate().hashCode());
        result = prime * result + ((getLength() == null) ? 0 : getLength().hashCode());
        result = prime * result + ((getSubdate() == null) ? 0 : getSubdate().hashCode());
        result = prime * result + ((getEstatus() == null) ? 0 : getEstatus().hashCode());
        result = prime * result + ((getReldate() == null) ? 0 : getReldate().hashCode());
        result = prime * result + ((getEid() == null) ? 0 : getEid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", nid=").append(nid);
        sb.append(", title=").append(title);
        sb.append(", context=").append(context);
        sb.append(", startdate=").append(startdate);
        sb.append(", enddate=").append(enddate);
        sb.append(", length=").append(length);
        sb.append(", subdate=").append(subdate);
        sb.append(", estatus=").append(estatus);
        sb.append(", reldate=").append(reldate);
        sb.append(", eid=").append(eid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}