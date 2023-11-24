package kr.ac.brother.newsjin.board.repository;

import kr.ac.brother.newsjin.board.dto.response.BoardWithoutCommentResponseDto;
import kr.ac.brother.newsjin.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByAll(String type);
    List<Board> findAllByOderByCreatedAtDesc(String type);
    List<Board> findByFollowOderByDesc(String type);
    List<Board> findByLikeOderByDesc(String type);
}
