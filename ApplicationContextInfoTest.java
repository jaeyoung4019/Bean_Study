package shop.core.BeanFind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import shop.core.AppConfig;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){ // iter + tab 누르면 자동 포문
        String [] bean = ac.getBeanDefinitionNames();
        for (String s : bean) {
            Object beans = ac.getBean(s);
            System.out.println("name = " + s + "  , " + beans);
        }
    }


    @Test
    @DisplayName("에플리케이션 빈 출력하기")
    void findApplicationBean(){ // iter + tab 누르면 자동 포문
        String [] bean = ac.getBeanDefinitionNames();
        for (String s : bean) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(s); // 빈 하나하나에 대한 정보
            // Role ROLE_APLLICATION : 직접등록한 에플리케이션 빈
            // Role ROLE_Infrastrucure : 스프링 내부에서 사용하는 빈
            //내가 에플리케이션을 개발하기 위해 등록한 빈을 검색
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object beank = ac.getBean(s);
                System.out.println("name = " + s + "  , " + beank);
            }
        }
    }
}
