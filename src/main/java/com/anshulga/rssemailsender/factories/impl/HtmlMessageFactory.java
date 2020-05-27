package com.anshulga.rssemailsender.factories.impl;

import com.anshulga.rssemailsender.entities.rss.RssChannel;
import com.anshulga.rssemailsender.entities.rss.RssDto;
import com.anshulga.rssemailsender.entities.rss.RssItem;
import com.anshulga.rssemailsender.factories.RssMessageFactory;
import com.anshulga.rssemailsender.services.Converter;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HtmlMessageFactory implements RssMessageFactory {
    private final Converter converter;

    public HtmlMessageFactory(Converter converter) {
        this.converter = converter;
    }

    @Override
    public String getRssMessage(final Set<String> rssLinks) {
        List<RssDto> rssDtoList = rssLinks.stream()
                .map(this::getRss)
                .collect(Collectors.toList());
        return converter.convert(rssDtoList);
    }

    @Override
    public RssDto getRss(final String url) {
        SyndFeed feed;
        try (XmlReader reader = new XmlReader(new URL(url))) {
            feed = new SyndFeedInput().build(reader);
        } catch (IOException | FeedException e) {
            throw new RuntimeException(e);
        }
        return RssDto.builder()
                .channel(buildChannelInfo(feed))
                .items(buildItems(feed))
                .build();
    }

    private List<RssItem> buildItems(final SyndFeed feed) {
        return feed.getEntries().stream()
                .map(x -> RssItem.builder()
                        .title(x.getTitle())
                        .link(x.getLink())
                        .description(x.getDescription().getValue())
                        .build())
                .collect(Collectors.toList());
    }

    private RssChannel buildChannelInfo(final SyndFeed feed) {
        return RssChannel.builder()
                .title(feed.getTitle())
                .link(feed.getLink())
                .description(feed.getDescription())
                .build();
    }
}
