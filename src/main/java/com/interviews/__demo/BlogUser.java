package com.interviews.__demo;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name="blog_user")
public class BlogUser {

    private @Id
    @GeneratedValue Long userId;

    private String username;

    private String createdAt;

    @OneToMany(mappedBy = "blogUser", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<BlogPost> posts;

    @OneToMany(mappedBy = "blogUser", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<BlogPostComment> comments;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        BlogUser blogUser = (BlogUser) o;
        return getUserId() != null && Objects.equals(getUserId(), blogUser.getUserId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}