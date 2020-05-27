package com.anshulga.rssemailsender.entities.rss;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RssItem {
    private String title;
    private String link;
    private String description;
}
