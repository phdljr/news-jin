package kr.ac.brother.newsjin.boardlike.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import kr.ac.brother.newsjin.board.entity.Board;
import kr.ac.brother.newsjin.boardlike.entity.BoardLike;
import kr.ac.brother.newsjin.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {

    Long countByBoard(Board board);

    List<BoardLike> findByUser(User user);

    Optional<BoardLike> findByUserAndBoardId(User loginUser, Long boardId);
}
