package kr.ac.brother.newsjin.boardlike.service;

import kr.ac.brother.newsjin.user.entity.User;

public interface BoardLikeService {

    void likeBoard(Long boardId, User user);

    void unlikeBoard(Long boardId, User user);
}
