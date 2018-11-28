/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author King
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findByWin", query = "SELECT u FROM User u WHERE u.win = :win"),
    @NamedQuery(name = "User.findByLose", query = "SELECT u FROM User u WHERE u.lose = :lose"),
    @NamedQuery(name = "User.findByDraw", query = "SELECT u FROM User u WHERE u.draw = :draw"),
    @NamedQuery(name = "User.findByPass", query = "SELECT u FROM User u WHERE u.pass = :pass")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "win")
    private BigInteger win;
    @Column(name = "lose")
    private BigInteger lose;
    @Column(name = "draw")
    private BigInteger draw;
    @Basic(optional = false)
    @Column(name = "pass")
    private String pass;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id1")
    private Collection<History> historyCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id2")
    private Collection<History> historyCollection1;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }
    public User(String name, String pass) {
        
        this.name = name;
        this.pass = pass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getWin() {
        return win;
    }

    public void setWin(BigInteger win) {
        this.win = win;
    }

    public BigInteger getLose() {
        return lose;
    }

    public void setLose(BigInteger lose) {
        this.lose = lose;
    }

    public BigInteger getDraw() {
        return draw;
    }

    public void setDraw(BigInteger draw) {
        this.draw = draw;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @XmlTransient
    public Collection<History> getHistoryCollection() {
        return historyCollection;
    }

    public void setHistoryCollection(Collection<History> historyCollection) {
        this.historyCollection = historyCollection;
    }

    @XmlTransient
    public Collection<History> getHistoryCollection1() {
        return historyCollection1;
    }

    public void setHistoryCollection1(Collection<History> historyCollection1) {
        this.historyCollection1 = historyCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "name :" + name + "    pass :"+pass ;
    }
    
}
