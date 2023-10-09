package shop.core.BeanFind;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import shop.core.AppConfig;
import shop.core.member.MemberService;
import shop.core.member.MemberServiceImpl;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

//control + e 이전코드
 class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


    @Test
    @DisplayName("빈 이름으로 조회해봅세")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService" , MemberService.class);
        assertThat(memberService).isExactlyInstanceOf(MemberServiceImpl.class); // 객체간 비교해서 테스트 해보는거 instanceOf 기억나니 병철갓
    }

    @Test
    @DisplayName("빈 이름X으로 조회해봅세")
    void findBeanByNameX(){ // NoSuchBeanDefinitionException: No bean named 'member' available 검증해야함
        //MemberService member = ac.getBean("member" , MemberService.class);  // Bean 을 들고오는건데 xml 설정할때 <Bean> 했던부분 지정해서 들고오는거라고 생각하면 편함
        //assertThat(memberService).isExactlyInstanceOf(MemberServiceImpl.class);
        assertThrows(NoSuchBeanDefinitionException.class,
                ()-> ac.getBean("member" , MemberService.class));
    }

    @Test
    @DisplayName("타입으로 조회해봅세")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isExactlyInstanceOf(MemberServiceImpl.class);  // 구현체로 비교할 것 값이 있어야 하니까
    }

    @Test
    @DisplayName("구체 타입으로 조회해봅세") // 되긴하지만 별로 안좋은 코드
    void findBeanByName2(){
        MemberService memberService = ac.getBean("memberService" , MemberServiceImpl.class); // 구체된거 왠만하면 쓰지마시길
        assertThat(memberService).isExactlyInstanceOf(MemberServiceImpl.class); // 상속받은거니까 instanceOf 쓰는거임.
    }
}
