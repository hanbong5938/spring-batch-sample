package com.moow.demo.global.job;

import com.moow.demo.domain.ChangeDomain;
import com.moow.demo.domain.ChangeDomainRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class ChangeDomainWriter implements ItemWriter<ChangeDomain> {

    private final ChangeDomainRepository changeDomainRepository;

    @Override
    public void write(final Chunk<? extends ChangeDomain> chunk) {
        log.debug("write data");
        changeDomainRepository.saveAll(chunk.getItems());
    }
}