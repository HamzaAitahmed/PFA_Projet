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
public interface TeamRepository extends JpaRepository<Team,Integer>{

    @Query("SELECT p.ProjectTeam FROM Project p WHERE p.ProjectTeam IS NOT NULL ")
    List<Team> findNotNullProjects();

    @Query("SELECT p.ProjectTeam FROM Project p WHERE p.ProjectTeam IS NOT NULL ")
    List<Team> findTeamById();

    Team findTeamById(Integer id);

}
