package nce.majorproject.repositories;

import nce.majorproject.entities.Admin;
import nce.majorproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "select u from User u where u.userName=?1")
    Optional<User> validateUserName(String userName);

    @Query(value = "select u from User u where u.userName=?1 and u.pasword=?2")
    Optional<User> authenticateUserCredential(String username, String password);

    @Query(value = "select u from User u where u.id=?1")
    Optional<User>validateUserById(Long id);
}
