package kr.ac.brother.newsjin.boardlike.repository;

import kr.ac.brother.newsjin.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikeRepository extends JpaRepository<Board, Long> {

    Long countByBoard(Board board);
}
