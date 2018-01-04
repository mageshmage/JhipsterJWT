package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A UVUser.
 */
@Entity
@Table(name = "uv_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UVUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "jhi_password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "is_verified_user")
    private Boolean isVerifiedUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public UVUser userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public UVUser password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public UVUser email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean isIsVerifiedUser() {
        return isVerifiedUser;
    }

    public UVUser isVerifiedUser(Boolean isVerifiedUser) {
        this.isVerifiedUser = isVerifiedUser;
        return this;
    }

    public void setIsVerifiedUser(Boolean isVerifiedUser) {
        this.isVerifiedUser = isVerifiedUser;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UVUser uVUser = (UVUser) o;
        if (uVUser.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), uVUser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UVUser{" +
            "id=" + getId() +
            ", userName='" + getUserName() + "'" +
            ", password='" + getPassword() + "'" +
            ", email='" + getEmail() + "'" +
            ", isVerifiedUser='" + isIsVerifiedUser() + "'" +
            "}";
    }
}
