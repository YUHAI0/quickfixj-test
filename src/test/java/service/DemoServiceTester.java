package service;

import com.dao.DemoRepository;
import com.entity.DemoEntity;
import com.entity.Person;
import com.service.DemoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
public class DemoServiceTester {

    @TestConfiguration
    static class DemoServiceTestContextConfiguration {

        @Bean
        public DemoService demoService() {
            return new DemoService();
        }

    }

    @Autowired
    private DemoService demoService;

    @MockBean
    private DemoRepository demoRepository;

    @Before
    public void setUp() {
        DemoEntity one = new DemoEntity(
                "string11", "string2", 1.1, 2, 3, new Person("name"));
        Mockito.when(demoRepository.findFirstByString2("string2"))
                .thenReturn(one);

    }

    @Test
    public void test() {
        String string2 = "string2";
        DemoEntity found = demoService.getDemoEntityByString2(string2);

        assertThat(found.getString2()).isEqualTo(string2);
    }

}
