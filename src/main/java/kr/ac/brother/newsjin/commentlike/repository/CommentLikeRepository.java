package kr.ac.brother.newsjin.commentlike.repository;

import java.util.Optional;
import kr.ac.brother.newsjin.commentlike.entity.CommentLike;
import kr.ac.brother.newsjin.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    Optional<CommentLike> findByUser(User user);
}
