package kr.ac.brother.newsjin.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.ac.brother.newsjin.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_USER")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String imageName;

    @Column
    private String imagePath;

    @Column
    private String intro;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Builder
    public User(final String username, final String nickname, final String email,
        final String password, final String imageName,
        final String imagePath, final String intro, final UserRole role) {
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.imageName = imageName;
        this.imagePath = imagePath;
        this.intro = intro;
        this.role = role;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateIntro(final String intro) {
        this.intro = intro;
    }

    public void updatePassword(final String password) {
        this.password = password;
    }

    public void uploadImage(final String imageName, final String imagePath) {
        this.imageName = imageName;
        this.imagePath = imagePath;
    }

    public void deleteImage() {
        imageName = null;
        imagePath = null;
    }
}
