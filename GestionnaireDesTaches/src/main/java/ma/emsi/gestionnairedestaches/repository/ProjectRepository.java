package ma.emsi.gestionnairedestaches.repository;

import ma.emsi.gestionnairedestaches.model.*;
import jakarta.transaction.Transactional;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> {

    @Query("SELECT p FROM Project p WHERE p.ProjectOwner.id = :id")
    List<Project> findByProjectOwner(@Param("id") Integer id);

    @Query("SELECT t.Projects FROM Team t Join t.Members m on m.id = :id")
    List<Project> findMemberById(int id);

    Project findProjectById(Integer id);


}
