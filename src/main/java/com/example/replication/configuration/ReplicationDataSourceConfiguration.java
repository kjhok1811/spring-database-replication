package com.example.replication.configuration;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class ReplicationDataSourceConfiguration {
    private final ReplicationDataSourceProperties replicationDataSourceProperties;

    @Bean
    public DataSource routingDataSource() {
        ReplicationRoutingDataSource replicationRoutingDataSource = new ReplicationRoutingDataSource();

        ReplicationDataSourceProperties.Write write = replicationDataSourceProperties.getWrite();
        DataSource writeDataSource = createDataSource(write.getUrl());

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(write.getName(), writeDataSource);

        List<ReplicationDataSourceProperties.Read> reads = replicationDataSourceProperties.getReads();
        for (ReplicationDataSourceProperties.Read read : reads) {
            dataSourceMap.put(read.getName(), createDataSource(read.getUrl()));
        }

        replicationRoutingDataSource.setDefaultTargetDataSource(writeDataSource);
        replicationRoutingDataSource.setTargetDataSources(dataSourceMap);
        return replicationRoutingDataSource;
    }

    @Bean
    @Primary
    public DataSource lazyDataSource() {
        return new LazyConnectionDataSourceProxy(routingDataSource());
    }

    private DataSource createDataSource(String url) {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(replicationDataSourceProperties.getDriverClassName());
        hikariDataSource.setUsername(replicationDataSourceProperties.getUsername());
        hikariDataSource.setPassword(replicationDataSourceProperties.getPassword());
        hikariDataSource.setJdbcUrl(url);
        return hikariDataSource;
    }
}
