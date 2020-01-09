package ru.pavel2107.arch.hzcatalog.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientUserCodeDeploymentConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.config.UserCodeDeploymentConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    @Bean
    public ClientConfig clientConfig(){
        ClientConfig config = new ClientConfig();
        GroupConfig groupConfig = config.getGroupConfig();
        groupConfig.setName("dev");
        groupConfig.setPassword("dev-pass");

        config.setClassLoader(Thread.currentThread().getContextClassLoader());

        return config;
    }

    @Bean
    public HazelcastInstance hazelcastInstance( ClientConfig config){
        HazelcastInstance hazelcastInstanceClient = HazelcastClient.newHazelcastClient(config);
        return hazelcastInstanceClient;
    }

}
