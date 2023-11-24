package kr.ac.brother.newsjin.board.repository;

import java.util.List;
import kr.ac.brother.newsjin.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByOrderByCreateAtDesc();
}
