package com.example.dddk.domain;
import lombok.*;
import javax.persistence.*;
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity // DB의 테이블
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENT
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Transient
    @Enumerated
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}

//@Getter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity(name="user") // DB의 테이블, JPA를 사용할 클래스를 명시하며, 테이블과 매핑하는 역할
//
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENT
//    private Long id;
//
//    @Column(nullable = false)
//    private String firstname;
//
//    @Column(nullable = false)
//    private String lastname;
//
//    @Builder
//    public User(String firstname, String lastname) {
//        this.firstname = firstname;
//        this.lastname = lastname;
//    }
//}