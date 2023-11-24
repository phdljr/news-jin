package kr.ac.brother.newsjin.boardlike.service.impl;

import java.util.Optional;
import kr.ac.brother.newsjin.board.entity.Board;
import kr.ac.brother.newsjin.board.exception.NotFoundBoardException;
import kr.ac.brother.newsjin.board.repository.BoardRepository;
import kr.ac.brother.newsjin.boardlike.entity.BoardLike;
import kr.ac.brother.newsjin.boardlike.exception.AlreadyExistBoardLikeException;
import kr.ac.brother.newsjin.boardlike.exception.NotFoundBoardLikeException;
import kr.ac.brother.newsjin.boardlike.repository.BoardLikeRepository;
import kr.ac.brother.newsjin.boardlike.service.BoardLikeService;
import kr.ac.brother.newsjin.user.entity.User;
import kr.ac.brother.newsjin.user.exception.NotFoundUserException;
import kr.ac.brother.newsjin.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardLikeServiceImpl implements BoardLikeService {

    private final BoardRepository boardRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final UserRepository userRepository;

    @Override
    public void likeBoard(final Long boardId, final User user) {
        User loginUser = userRepository.findById(user.getId())
            .orElseThrow(NotFoundUserException::new);

        Optional<BoardLike> findBoardLike =
            boardLikeRepository.findByUserAndBoardId(loginUser, boardId);
        if (findBoardLike.isPresent()) {
            throw new AlreadyExistBoardLikeException();
        }

        Board board = boardRepository.findById(boardId).orElseThrow(NotFoundBoardException::new);

        BoardLike boardLike = BoardLike.builder()
            .board(board)
            .user(loginUser)
            .build();

        boardLikeRepository.save(boardLike);
    }

    @Override
    public void unlikeBoard(final Long boardId, final User user) {
        User loginUser = userRepository.findById(user.getId())
            .orElseThrow(NotFoundUserException::new);

        Optional<BoardLike> findBoardLike =
            boardLikeRepository.findByUserAndBoardId(loginUser, boardId);
        if (findBoardLike.isEmpty()) {
            throw new NotFoundBoardLikeException();
        }

        BoardLike boardLike = findBoardLike.get();
        boardLikeRepository.delete(boardLike);
    }
}
