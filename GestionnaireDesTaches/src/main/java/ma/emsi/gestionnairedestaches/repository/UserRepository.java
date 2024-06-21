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

import java.util.Collection;
import java.util.List;

@Transactional
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    User findUserById(@Param("id") int id);

//    @Query("SELECT t.Members FROM Team t JOIN Project p ON p.id = :projectId AND p IN ( SELECT t.Projects  FROM Team t)")
//    @Query("SELECT t.Members FROM Team t  where t IN (SELECT ProjectTeam from Project where Project.id = :projectID )")
//    List<User> findUsersByProjectId(int projectId);

//    @Query("SELECT u FROM User u where u.id IN (Select tm.id from Team.Members tm where Team.id = :TeamId ) OR u IN (Select t.Leader from Team t where t.id = :TeamId )")
    @Query("select u from User u Join Team On u IN ( Select t.Members from Team t where t.id = :TeamId ) ")
    Collection<User> findMembersByTeamId( Integer TeamId);

}
