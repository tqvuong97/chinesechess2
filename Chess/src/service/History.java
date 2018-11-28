/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author King
 */
@Entity
@Table(name = "history")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "History.findAll", query = "SELECT h FROM History h"),
    @NamedQuery(name = "History.findByIdHis", query = "SELECT h FROM History h WHERE h.idHis = :idHis"),
    @NamedQuery(name = "History.findByStatus", query = "SELECT h FROM History h WHERE h.status = :status")})
public class History implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idHis")
    private Long idHis;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "id1", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User id1;
    @JoinColumn(name = "id2", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User id2;

    public History() {
    }

    public History(Long idHis) {
        this.idHis = idHis;
    }

    public History(Long idHis, String status) {
        this.idHis = idHis;
        this.status = status;
    }

    public Long getIdHis() {
        return idHis;
    }

    public void setIdHis(Long idHis) {
        this.idHis = idHis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getId1() {
        return id1;
    }

    public void setId1(User id1) {
        this.id1 = id1;
    }

    public User getId2() {
        return id2;
    }

    public void setId2(User id2) {
        this.id2 = id2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHis != null ? idHis.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof History)) {
            return false;
        }
        History other = (History) object;
        if ((this.idHis == null && other.idHis != null) || (this.idHis != null && !this.idHis.equals(other.idHis))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "chinesechess.History[ idHis=" + idHis + " ]";
    }
    
}
