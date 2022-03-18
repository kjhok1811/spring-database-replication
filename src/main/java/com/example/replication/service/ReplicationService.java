package com.example.replication.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.sql.DataSource;
import java.sql.Connection;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReplicationService {
    private final DataSource lazyDataSource;

    @Transactional(readOnly = true)
    public void read() {
        try (Connection connection = lazyDataSource.getConnection()){
            log.info("read url : {}", connection.getMetaData().getURL());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Transactional
    public void write() {
        try (Connection connection = lazyDataSource.getConnection()){
            log.info("write url : {}", connection.getMetaData().getURL());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
