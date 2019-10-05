package cn.edu.mju.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/26
 */
@SpringBootApplication
public class ManagerBootstrap {

    public static void main(String[] args) {

        new SpringApplicationBuilder(ManagerBootstrap.class).run(args);

    }


}
