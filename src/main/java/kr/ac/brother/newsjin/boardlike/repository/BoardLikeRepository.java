package kr.ac.brother.newsjin.boardlike.repository;

import java.util.Optional;
import kr.ac.brother.newsjin.boardlike.entity.BoardLike;
import kr.ac.brother.newsjin.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {

    Optional<BoardLike> findByUserAndBoardId(User loginUser, Long boardId);
}
