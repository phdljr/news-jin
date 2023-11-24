package kr.ac.brother.newsjin.follow.repository;

import java.util.List;
import java.util.Optional;
import kr.ac.brother.newsjin.follow.entity.Follow;
import kr.ac.brother.newsjin.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    boolean existsByUserAndFollowingUser(User user, final User followingUser);

    Optional<Follow> findByUserAndFollowingUser(User user, User followingUser);

    List<Follow> findByUser(User user);
}
