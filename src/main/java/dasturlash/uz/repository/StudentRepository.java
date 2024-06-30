package dasturlash.uz.repository;

import dasturlash.uz.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer>,
        PagingAndSortingRepository<StudentEntity, Integer> {

    Page<StudentEntity> findByName(String name, Pageable pageable);

    Page<StudentEntity> findByNameAndSurname(String name, String surname, Pageable pageable);

    @Query("select s FROM  StudentEntity s where s.name =?1 and s.age=?2")
    Page<StudentEntity> findAllByNameAndAge(String name, int age, Pageable pageable);

}
