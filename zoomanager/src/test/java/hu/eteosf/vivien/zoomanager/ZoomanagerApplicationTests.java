package hu.eteosf.vivien.zoomanager;

import hu.eteosf.vivien.zoomanager.service.DefaultService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootApplication
@ComponentScan(
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = DefaultService.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ZoomanagerApplication.class)
        }
)
public class ZoomanagerApplicationTests {

    public static void main(String[] args) {

        SpringApplication.run(ZoomanagerApplicationTests.class, args);
    }
}