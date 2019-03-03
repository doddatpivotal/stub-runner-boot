package io.pivotal.stubrunner;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import groovy.transform.CompileStatic;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.contract.stubrunner.HttpServerStubConfiguration;
import org.springframework.cloud.contract.stubrunner.provider.wiremock.WireMockHttpServerStubConfigurer;
import org.springframework.cloud.contract.stubrunner.server.EnableStubRunnerServer;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.util.SocketUtils;

@SpringBootApplication
@EnableStubRunnerServer
@EnableDiscoveryClient
public class CfStubRunnerBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(CfStubRunnerBootApplication.class, args);
    }

    @AutoConfigureStubRunner(httpServerStubConfigurer = HttpsForFortuneService.class)
    static class Config {
    }

    @CompileStatic
    static class HttpsForFortuneService extends WireMockHttpServerStubConfigurer {

        private static final Log log = LogFactory.getLog(HttpsForFortuneService.class);

        @Override
        public WireMockConfiguration configure(WireMockConfiguration httpStubConfiguration, HttpServerStubConfiguration httpServerStubConfiguration) {
            if (httpServerStubConfiguration.stubConfiguration.getArtifactId() == "fortune-service") {
                int httpsPort = SocketUtils.findAvailableTcpPort();
                log.info("Will set HTTPs port [" + httpsPort + "] for fraud detection server");
                return httpStubConfiguration.httpsPort(httpsPort);
            }
            return httpStubConfiguration;
        }

    }

}

