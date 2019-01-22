package com.dao;

import com.entity.DemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface DemoRepository extends JpaRepository<DemoEntity, Integer> {

    /**
     * Example for normal use
     */

    /* and */
    List<DemoEntity> findByString1AndDouble1(String string1, Double double1);

    /* or */
    List<DemoEntity> findByString1OrString2(String string1, String string2);

    /* between */
    List<DemoEntity> findByInt1Between(Integer low, Integer high);

    /* null */
    List<DemoEntity> findByString2IsNull();
    List<DemoEntity> findByString2NotNull();

    /* like */
    List<DemoEntity> findByString1Like(String like);
    List<DemoEntity> findByString1NotLike(String like);

    /* in */
    List<DemoEntity> findByInt1In(Collection<Integer> intList);
    List<DemoEntity> findByInt1NotIn(Collection<Integer> intList);

    /**
     * Example for normal use
     */

    /* order by */
    List<DemoEntity> findByString1OrderByString2Desc(String string1);
    List<DemoEntity> findByString1OrderByString2Asc(String string1);


    /**
     * Example for JoinColumn's field
     **/

    /* */
    List<DemoEntity> findByPersonId(Integer personId);
    Long countByPersonId(Integer personId);
    void remove(Integer personId);

    /**
     * Example for custom query use
     */

    /* native SQL */
    @Query(value = "SELECT * FROM demo_entity WHERE id = ?1", nativeQuery = true)
    List<DemoEntity> findByIdNative(Integer id);

    /* HQL */
    @Query(value = "SELECT * FROM demo_entity WHERE string_1 = :string1", nativeQuery = true)
    List<DemoEntity> findByString1Hql(String string1);

}
