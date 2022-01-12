package org.kenshin.springbootminio.config;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;



@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {

    private String endpoint;

    private int port;

    private String accessKey;

    private String secretKey;

    private Boolean secure;

    private String bucketName;

    private String configDir;

    @Bean
    public MinioClient getMinioClient(){
        MinioClient minioClient = MinioClient.builder().endpoint(this.endpoint, this.port, false).credentials(this.accessKey, this.secretKey).build();
        return minioClient;
    }
}
