package com.ebsco.training.bookmiddle.config;

import com.netflix.appinfo.AmazonInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.commons.util.InetUtilsProperties;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDiscoveryClient
@ConditionalOnProperty(value="eureka.registration.enabled")
public class EurekaClientConfig {

    @Value("${server.port}")
	private int serverPort;
    
	// TODO instead of making this dependent on yet another switch, we could simply 
	//  always run it and make it dependent on whether the AWS_ENV environment variable is set
    @Bean
    @ConditionalOnProperty(value="eureka.aws.registration.enabled")
    public EurekaInstanceConfigBean eurekaInstanceConfig() {
       InetUtilsProperties properties = new InetUtilsProperties();
        
        // Just set these as zero length strings
        properties.setDefaultIpAddress("");
        properties.setDefaultHostname("");

        InetUtils inetUtils = new InetUtils(properties);
        
        EurekaInstanceConfigBean b = new EurekaInstanceConfigBean(inetUtils);
        AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
        info.getMetadata().put(AmazonInfo.MetaDataKey.publicHostname.getName(), info.get(AmazonInfo.MetaDataKey.localIpv4));
        b.setDataCenterInfo(info);

        b.setHostname(info.get(AmazonInfo.MetaDataKey.localHostname));
        b.setIpAddress(info.get(AmazonInfo.MetaDataKey.localIpv4));
        
        b.setSecurePortEnabled(false);
        b.setNonSecurePortEnabled(true);
        b.setNonSecurePort(serverPort);
        
        return b;
    }
}
