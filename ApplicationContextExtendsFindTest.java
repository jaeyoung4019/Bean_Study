package shop.core.BeanFind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import shop.core.discount.FixDiscountPolicy;
import shop.core.discount.RateDiscountPolicy;
import shop.core.discount.discountPolicy;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextExtendsFindTest {
    //기본 기능 , 스프링컨테이너 검증기능을 써야하기 때문에 공부해야함.
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모타입으로 조회하면 자식이 2이상일 때 오류 발생")
    void findBeanByParentTypeDuplicate(){
        //discountPolicy bean = ac.getBean(discountPolicy.class);
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(discountPolicy.class));
    }

    @Test
    @DisplayName("부모타입으로 조회할 때 자식이 둘 이상이면 네임으로 지정하면 된다.")
    void findBeanByParentTypeBeanName(){
        discountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy" , discountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }


    @Test
    @DisplayName("특정하위타입으로 조회")
    void findBeanSubType(){
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모타입으로 모두 조회")
    void findAllBean(){
        Map<String, discountPolicy> beansOfType = ac.getBeansOfType(discountPolicy.class); // discountPolicy 는 인터페이스 구현객체아님
        assertThat(beansOfType.size()).isEqualTo(2); // 두값 비교하는 jUnit 메소드  import static org.assertj.core.api.Assertions.*; 스태틱 임포트 걸려있는거얌
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " values =" + beansOfType.get(key)); // 테스트에서 출력문 쓰지말것을 당부함
        }
    }

    @Test
    @DisplayName("부모타입으로 모두 조회 object")
    void findAllObject(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class); // 이 겟 빈즈오브타입을 쓰면 그 타입에 맞는 빈즈들을 검색해서 검증함.
        for (String key : beansOfType.keySet()) { // 타입이 맵형으로 나오니까 인핸즈드 돌려서 검증
            System.out.println("key = " + key + " values = " + beansOfType.get(key));
        }
    }

    @Configuration
    static class TestConfig{
        @Bean
        public discountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public discountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }

}
