package kr.ac.brother.newsjin.board.repository;

import kr.ac.brother.newsjin.board.dto.response.BoardWithoutCommentResponseDto;
import kr.ac.brother.newsjin.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<BoardWithoutCommentResponseDto> findByAll(String type);
    List<BoardWithoutCommentResponseDto> findAllByOderByCreatedAtDesc(String type);
    List<BoardWithoutCommentResponseDto> findByFollowOderByDesc(String type);
    List<BoardWithoutCommentResponseDto> findByLikeOderByDesc(String type);
}
