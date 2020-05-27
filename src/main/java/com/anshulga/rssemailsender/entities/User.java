package com.anshulga.rssemailsender.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    private UUID id;
    @Column(nullable = false)
    private String email;
    @ElementCollection
    @CollectionTable(name = "RSSLinks", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "rssLink")
    private Set<String> rssLinks;

    public void setRssLinks(Set<String> rssLinks) {
        if (this.rssLinks == null) {
            this.rssLinks = rssLinks;
            return;
        }
        this.rssLinks.clear();
        this.rssLinks.addAll(rssLinks);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", rssLinks=" + rssLinks +
                '}';
    }
}
