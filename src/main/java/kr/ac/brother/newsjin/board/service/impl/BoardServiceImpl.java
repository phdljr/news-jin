package kr.ac.brother.newsjin.board.service.impl;

import kr.ac.brother.newsjin.board.dto.request.BoardRequestDto;
import kr.ac.brother.newsjin.board.dto.response.BoardResponseDto;
import kr.ac.brother.newsjin.board.dto.response.BoardWithCommentResponseDto;
import kr.ac.brother.newsjin.board.dto.response.BoardWithoutCommentResponseDto;
import kr.ac.brother.newsjin.board.entity.Board;
import kr.ac.brother.newsjin.board.exception.NotFoundBoardException;
import kr.ac.brother.newsjin.board.exception.NotMatchUserException;
import kr.ac.brother.newsjin.board.repository.BoardRepository;
import kr.ac.brother.newsjin.board.service.BoardService;
import kr.ac.brother.newsjin.boardlike.repository.BoardLikeRepository;
import kr.ac.brother.newsjin.comment.dto.response.CommentResponseDTO;
import kr.ac.brother.newsjin.follow.repository.FollowRepository;
import kr.ac.brother.newsjin.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final FollowRepository followRepository;

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
    public BoardResponseDto updateBoard(Long boardId, BoardRequestDto boardRequestDto, User user) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(NotFoundBoardException::new);
        if (!user.getId().equals(board.getUser().getId())) {
            throw new NotMatchUserException();
        }
        board.update(boardRequestDto);

        return new BoardResponseDto(board);
    }

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
    public BoardWithCommentResponseDto getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).get();
        List<CommentResponseDTO> comments = board.getComments().stream().map(CommentResponseDTO::new).toList();

        return BoardWithCommentResponseDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .createdAt(board.getCreateAt())
                .comments(comments)
                .build();
    }

    @Transactional(readOnly = true)
    public List<BoardWithoutCommentResponseDto> getBoards(String type, User user) {
        List<Board> boardList = null;
        List<BoardWithoutCommentResponseDto> boardWithoutCommentResponseDtoList = new ArrayList<>();

        if (type.equals("all")) {
            boardList = boardRepository.findByAll(type).stream().toList();
        } else if (type.equals("recent")) {
            boardList = boardRepository.findAllByOderByCreatedAtDesc(type).stream().toList();
        } else if (type.equals("follow")) {
            boardList = boardRepository.findByFollowOderByDesc(type).stream().toList();
        } else if (type.equals("like")) {
            boardList = boardRepository.findByLikeOderByDesc(type).stream().toList();
        }

        for (Board board : boardList) {
            boardWithoutCommentResponseDtoList
                    .add(BoardWithoutCommentResponseDto.builder()
                            .id(board.getId())
                            .title(board.getTitle())
                            .content(board.getContent())
                            .likes(boardLikeRepository.countByBoard(board))
                            .build());
        }

        return boardWithoutCommentResponseDtoList;
    }

}
