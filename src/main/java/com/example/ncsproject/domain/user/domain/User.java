package com.example.ncsproject.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import java.util.Date;

@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(
        uniqueConstraints = @UniqueConstraint(
        name = "USER_UNIQUE",
        columnNames = {"name"}
    )
)
@Entity
public class User {

    @Id @Column(name = "account_id")
    private String accountId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birth;


    @Column(name = "max_correct", nullable = false)
    @ColumnDefault("0")
    private Integer maxCorrect;

    public User updateMaxCorrect(Integer score) {
        this.maxCorrect = Math.max(this.maxCorrect, score);
        return this;
    }

    @Builder
    public User(String accountId, String name, String password, Date birth) {
        this.accountId = accountId;
        this.name = name;
        this.password = password;
        this.birth = birth;
    }
}
