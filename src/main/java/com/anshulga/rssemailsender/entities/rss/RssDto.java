package com.anshulga.rssemailsender.entities.rss;

import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RssDto {
    private RssChannel channel;
    private List<RssItem> items;
}
