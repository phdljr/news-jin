package kr.ac.brother.newsjin.follow.repository;

import kr.ac.brother.newsjin.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Board, Long> {

}
