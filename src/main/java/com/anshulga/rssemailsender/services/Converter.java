package com.anshulga.rssemailsender.services;

import com.anshulga.rssemailsender.entities.rss.RssDto;
import j2html.tags.DomContent;

import java.util.List;

public interface Converter {
    DomContent convert(RssDto rssDto);
    String convert(List<RssDto> rssList);
}
