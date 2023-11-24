package kr.ac.brother.newsjin.commentlike.service;

import kr.ac.brother.newsjin.user.entity.User;

public interface CommentLikeService {

    void likeComment(Long commentId, User user);

    void unlikeComment(Long commentId, User user);
}
