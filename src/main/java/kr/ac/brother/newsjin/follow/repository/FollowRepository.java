package kr.ac.brother.newsjin.follow.repository;

import java.util.List;
import kr.ac.brother.newsjin.board.entity.Board;
import kr.ac.brother.newsjin.follow.entity.Follow;
import kr.ac.brother.newsjin.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Board, Long> {
    List<Follow> findByUser(User user);
}
