package kr.ac.brother.newsjin.user.repository;

import java.util.Optional;
import kr.ac.brother.newsjin.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByNickname(String nickname);
}
