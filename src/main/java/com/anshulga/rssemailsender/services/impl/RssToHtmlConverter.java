package com.anshulga.rssemailsender.services.impl;

import com.anshulga.rssemailsender.entities.rss.RssChannel;
import com.anshulga.rssemailsender.entities.rss.RssDto;
import com.anshulga.rssemailsender.entities.rss.RssItem;
import com.anshulga.rssemailsender.services.Converter;
import j2html.tags.DomContent;
import org.springframework.stereotype.Service;

import java.util.List;

import static j2html.TagCreator.*;


@Service
public class RssToHtmlConverter implements Converter {
    @Override
    public DomContent convert(RssDto rssDto) {
        return div(
                convertChannel(rssDto.getChannel()),
                div(
                        each(rssDto.getItems(), this::convertItem)
                )
        );
    }

    @Override
    public String convert(List<RssDto> rssList) {
        return div(
                each(rssList, this::convert)
        ).render();
    }

    private DomContent convertChannel(RssChannel channel) {
        return div(
                h2("Title : " + channel.getTitle()),
                p(" Link: ").with(
                        a(channel.getLink())
                ),
                p("Description : ").with(
                        span().withText(channel.getDescription())
                )
        );
    }

    private DomContent convertItem(RssItem item) {
        return div(
                h3("Title : " + item.getTitle()),
                p(" Link: ").with(
                        a(item.getLink())
                ),
                p("Description : ").with(
                        div(rawHtml(item.getDescription()))
                )
        );
    }
}
