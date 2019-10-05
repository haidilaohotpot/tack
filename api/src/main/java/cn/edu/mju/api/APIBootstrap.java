package cn.edu.mju.api;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/26
 */
@SpringBootApplication(scanBasePackages = "cn.edu.mju")
public class APIBootstrap {

    public static void main(String[] args) {

        new SpringApplicationBuilder(APIBootstrap.class)
                .web(WebApplicationType.SERVLET)
                .run(args);

    }

}
