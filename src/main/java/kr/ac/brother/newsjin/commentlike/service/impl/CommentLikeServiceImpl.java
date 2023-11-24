package kr.ac.brother.newsjin.commentlike.service.impl;

import java.util.Optional;
import kr.ac.brother.newsjin.comment.entity.Comment;
import kr.ac.brother.newsjin.comment.exception.NotFoundCommentException;
import kr.ac.brother.newsjin.comment.repository.CommentRepository;
import kr.ac.brother.newsjin.commentlike.entity.CommentLike;
import kr.ac.brother.newsjin.commentlike.exception.AlreadyExistCommentLikeException;
import kr.ac.brother.newsjin.commentlike.exception.NotFoundCommentLikeException;
import kr.ac.brother.newsjin.commentlike.repository.CommentLikeRepository;
import kr.ac.brother.newsjin.commentlike.service.CommentLikeService;
import kr.ac.brother.newsjin.user.entity.User;
import kr.ac.brother.newsjin.user.exception.NotFoundUserException;
import kr.ac.brother.newsjin.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentLikeServiceImpl implements CommentLikeService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;

    @Override
    public void likeComment(final Long commentId, final User user) {
        User loginUser = userRepository.findById(user.getId())
            .orElseThrow(NotFoundUserException::new);

        Optional<CommentLike> findCommentLike = commentLikeRepository.findByUserAndCommentId(
            loginUser, commentId);
        if (findCommentLike.isPresent()) {
            throw new AlreadyExistCommentLikeException();
        }

        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(NotFoundCommentException::new);

        CommentLike commentLike = CommentLike.builder()
            .comment(comment)
            .user(loginUser)
            .build();

        commentLikeRepository.save(commentLike);
    }

    @Override
    public void unlikeComment(final Long commentId, final User user) {
        User loginUser = userRepository.findById(user.getId())
            .orElseThrow(NotFoundUserException::new);

        Optional<CommentLike> findCommentLike = commentLikeRepository.findByUserAndCommentId(
            loginUser, commentId);
        if (findCommentLike.isEmpty()) {
            throw new NotFoundCommentLikeException();
        }

        CommentLike commentLike = findCommentLike.get();
        commentLikeRepository.delete(commentLike);
    }
}
