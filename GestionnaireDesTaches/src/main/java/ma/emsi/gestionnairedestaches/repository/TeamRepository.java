package ma.emsi.gestionnairedestaches.repository;

import ma.emsi.gestionnairedestaches.model.*;
import jakarta.transaction.Transactional;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team,Integer>{

    @Query("SELECT p.ProjectTeam FROM Project p WHERE p.ProjectTeam IS NOT NULL ")
    List<Team> findNotNullProjects();

//    @Query("SELECT p.ProjectTeam FROM Project p WHERE p.ProjectTeam IS NOT NULL ")
//    List<Team> findTeamById();

    Team findTeamById(Integer id);

    @Query("SELECT t FROM Team t WHERE t.Leader.id = :id")
    List<Team> findTeamsByLeader(@Param("id") Integer id);

    @Query("SELECT t FROM Team t WHERE t In (select u.Teams from User u  where u.id = :id )")
    List<Team> findTeamsByMember(@Param("id") Integer id);

    @Query("SELECT t FROM Team t WHERE t In (select u.Teams from User u  where u.id = :id ) or t.Leader.id = :id ")
    List<Team> findTeamsByUser(@Param("id") Integer id);

//    @Autowired
//    private TeamRepository teamRepository;

//    public List<Team> getAllTeams() {
//        return teamRepository.findAll();
//    }

//    public Optional<Team> getTeamById(Integer id) {
//        return teamRepository.findById(id);
//    }

//    public Team saveTeam(Team team) {
//        return teamRepository.save(team);
//    }

//    public void deleteTeam(Integer id) {
//        teamRepository.deleteById(id);
//    }
}
