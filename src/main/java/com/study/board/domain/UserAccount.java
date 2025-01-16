package com.study.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "userId"),
        @Index(columnList = "email", unique = true),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")

})
@Entity
public class UserAccount extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // 고유 ID

    @Setter
    @Column(nullable = false, length = 50)
    private String userId;  // User ID

    @Setter
    @Column(nullable = false)
    private String userPassword;    // User Password


    @Setter
    @Column(length = 100)
    private String email;   // Email

    @Setter
    @Column(length = 100, name = "nickname")
    private String nickName;    // Nick Name

    @Setter
    private String memo;    //memo


    protected UserAccount() {}

    private UserAccount(String userId, String userPassword, String memo) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.memo = memo;

    }

    public static UserAccount of(String userId, String userPassword, String email) {
        return new UserAccount(userId, userPassword, email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount userAccount)) return false;
        return id != null && id.equals(userAccount.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
