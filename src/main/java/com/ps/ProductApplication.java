package com.ps;

import com.ps.model.Product;
import com.ps.service.ProductService;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Stream;

@SpringBootApplication
@EnableSwagger2
public class ProductApplication {
    public static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ProductApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ProductApplication.class);
        Environment environment = app.run(args).getEnvironment();
        String port = environment.getProperty("server.port");
        logger.info("Application Started with port {} ", port);
        logger.info("API Document :  http://localhost:{}/swagger-ui.html", port==null?"8080":port);
        logger.info("Database Details: http://localhost:{}/h2-console  : access with URl : jdbc:h2:mem:emp_db , UserName:sa, password :  ",  port==null?"8080":port);
      //  opendeFaultBrowser(port);
    }


    /**
     * Add Static test data at beginning of application
     *
     * @param service
     * @return
     */
    @Bean
    public CommandLineRunner employees(ProductService service) {
        return args -> getStreamOfStaticProductData().forEach(service::addProduct);
    }


    private static Stream<Product> getStreamOfStaticProductData() {
        return Stream.of(
                new Product(null, "p_One", 1000),
                new Product(null, "p_Two", 2000),
                new Product(null, "p_Three", 3000));
    }

    private static void opendeFaultBrowser(String port) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI("http://localhost:" + port + "/swagger-ui.html"));
                //Desktop.getDesktop().browse(new URI("http://localhost:"+port+"/h2-console"));

            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }


//    @Bean
//    public MeterRegistryCustomizer<MeterRegistry> configurer(@Value("${spring.application.name}") String appName){
//        return registry -> registry.config().commonTags("application", appName);
//    }

}
