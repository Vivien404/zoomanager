package hu.eteosf.vivien.zoomanager;

import hu.eteosf.vivien.zoomanager.service.ZooService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class Config {

    @Bean
    ZooService getServiceImpl(){
        return Mockito.mock(ZooService.class) ;
    }
}
