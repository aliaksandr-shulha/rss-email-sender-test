package com.anshulga.rssemailsender.factories;

import com.anshulga.rssemailsender.entities.rss.RssDto;

import java.util.Set;

public interface RssMessageFactory {
    String getRssMessage(Set<String> rssLinks);
    RssDto getRss(String url);
}
