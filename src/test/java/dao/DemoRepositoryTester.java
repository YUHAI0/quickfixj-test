package dao;

import com.ApiApplication;
import com.dao.DemoRepository;
import com.entity.DemoEntity;
import com.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
@DataJpaTest
public class DemoRepositoryTester {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DemoRepository demoRepository;

    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        DemoEntity one = new DemoEntity(
                "string11", "string22", 1.1, 2, 3, new Person("name"));
        entityManager.persist(one);
        entityManager.flush();

        // when
        DemoEntity found = demoRepository.findFirstByString2("string22");

        // then
        assertThat(found.getDouble1()).isEqualTo(one.getDouble1());
    }

}
