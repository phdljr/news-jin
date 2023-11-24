package kr.ac.brother.newsjin.board.service.impl;

import java.util.ArrayList;
import java.util.List;
import kr.ac.brother.newsjin.board.dto.request.BoardRequestDto;
import kr.ac.brother.newsjin.board.dto.response.BoardResponseDto;
import kr.ac.brother.newsjin.board.dto.response.BoardWithCommentResponseDto;
import kr.ac.brother.newsjin.board.dto.response.BoardWithoutCommentResponseDto;
import kr.ac.brother.newsjin.board.entity.Board;
import kr.ac.brother.newsjin.board.exception.IllegalBoardTypeException;
import kr.ac.brother.newsjin.board.exception.NotFoundBoardException;
import kr.ac.brother.newsjin.board.exception.NotMatchUserException;
import kr.ac.brother.newsjin.board.repository.BoardRepository;
import kr.ac.brother.newsjin.board.service.BoardService;
import kr.ac.brother.newsjin.boardlike.entity.BoardLike;
import kr.ac.brother.newsjin.boardlike.repository.BoardLikeRepository;
import kr.ac.brother.newsjin.comment.dto.response.CommentWithUserResponseDTO;
import kr.ac.brother.newsjin.commentlike.repository.CommentLikeRepository;
import kr.ac.brother.newsjin.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final CommentLikeRepository commentLikeRepository;

    @Override
    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto, User user) {
        Board board = Board.builder()
            .title(boardRequestDto.getTitle())
            .content(boardRequestDto.getContent())
            .user(user)
            .build();

        board = boardRepository.save(board);

        return new BoardResponseDto(board);
    }

    @Transactional
    @Override
    public BoardResponseDto updateBoard(Long boardId, BoardRequestDto boardRequestDto, User user) {
        Board board = boardRepository.findById(boardId)
            .orElseThrow(NotFoundBoardException::new);
        if (!user.getId().equals(board.getUser().getId())) {
            throw new NotMatchUserException();
        }
        board.update(boardRequestDto);

        return new BoardResponseDto(board);
    }

    @Override
    public BoardResponseDto deleteBoard(Long boardId, User user) {
        Board board = boardRepository.findById(boardId)
            .orElseThrow(NotFoundBoardException::new);
        if (!user.getId().equals(board.getUser().getId())) {
            throw new NotMatchUserException();
        }
        boardRepository.delete(board);

        return new BoardResponseDto(board);
    }

    @Transactional(readOnly = true)
    @Override
    public BoardWithCommentResponseDto getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(NotFoundBoardException::new);

        List<CommentWithUserResponseDTO> comments = board.getComments()
            .stream()
            .map(comment -> {
                Long commentLikes = commentLikeRepository.countByComment(comment);
                return new CommentWithUserResponseDTO(comment, commentLikes);
            })
            .toList();

        return BoardWithCommentResponseDto.builder()
            .id(board.getId())
            .title(board.getTitle())
            .nickname(board.getUser().getNickname())
            .content(board.getContent())
            .likes(boardLikeRepository.countByBoard(board))
            .createdAt(board.getCreateAt())
            .comments(comments)
            .build();
    }

    @Transactional(readOnly = true)
    @Override
    public List<BoardWithoutCommentResponseDto> getBoards(String type) {
        List<Board> boardList;

        if (type.equals("all")) {
            boardList = boardRepository.findAll();
        } else if (type.equals("recent")) {
            boardList = boardRepository.findAllByOrderByCreateAtDesc();
        } else {
            throw new IllegalBoardTypeException();
        }

        return createBoardWithoutCommentResponseDto(boardList);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BoardWithoutCommentResponseDto> getBoardsAuth(String type, User user) {
        List<Board> boardList = null;

        if (type.equals("all")) {
            return getBoards("all");
        } else if (type.equals("recent")) {
            return getBoards("recent");
        } else if (type.equals("follow")) {
            boardList = findByFollow(user);
        } else if (type.equals("like")) {
            boardList = findByLike(user);
        }

        return createBoardWithoutCommentResponseDto(boardList);
    }

    private List<BoardWithoutCommentResponseDto> createBoardWithoutCommentResponseDto(
        List<Board> boards
    ) {
        List<BoardWithoutCommentResponseDto> boardWithoutCommentResponseDtoList = new ArrayList<>();
        for (Board board : boards) {
            boardWithoutCommentResponseDtoList
                .add(BoardWithoutCommentResponseDto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .likes(boardLikeRepository.countByBoard(board))
                    .createdAt(board.getCreateAt())
                    .build()
                );
        }
        return boardWithoutCommentResponseDtoList;
    }

    // 좋아요 리스트를 가져옴
    // 그 리스트로 게시글을 조회
    private List<Board> findByLike(final User user) {
        return boardLikeRepository.findByUser(user)
            .stream()
            .map(BoardLike::getBoard)
            .toList();
    }

    // 팔로우한 사용자의 게시글 조회
    private List<Board> findByFollow(User user) {
        return null;
    }

}
