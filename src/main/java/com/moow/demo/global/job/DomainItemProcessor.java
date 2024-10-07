package com.moow.demo.global.job;

import com.moow.demo.domain.ChangeDomain;
import com.moow.demo.domain.Domain;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class DomainItemProcessor implements ItemProcessor<Domain, ChangeDomain> {
    @Override
    public ChangeDomain process(final Domain item) {
        log.debug("process domain");
        return ChangeDomain.builder().name(item.getName()).build();
    }
}
