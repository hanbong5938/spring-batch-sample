package com.moow.demo.global.job;

import com.moow.demo.domain.Domain;
import com.moow.demo.global.utils.RandomStringGenerator;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class DomainItemReader implements ItemReader<Domain> {
    private int count = 0;

    @Override
    public Domain read() {
        int maxCount = 10;
        if (count < maxCount) {
            log.debug("read domain");
            count++;
            return Domain.builder().name(new RandomStringGenerator(10).generate()).build();
        } else {
            return null; // Signal end of data
        }
    }
}