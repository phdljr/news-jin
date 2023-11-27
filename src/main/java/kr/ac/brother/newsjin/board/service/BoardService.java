package kr.ac.brother.newsjin.board.service;

import java.util.List;
import kr.ac.brother.newsjin.board.dto.request.BoardRequestDto;
import kr.ac.brother.newsjin.board.dto.response.BoardResponseDto;
import kr.ac.brother.newsjin.board.dto.response.BoardWithCommentResponseDto;
import kr.ac.brother.newsjin.board.dto.response.BoardWithoutCommentResponseDto;
import kr.ac.brother.newsjin.user.entity.User;

public interface BoardService {

    BoardResponseDto createBoard(BoardRequestDto boardRequestDto, User user);

    BoardResponseDto updateBoard(Long boardId, BoardRequestDto boardRequestDto, User user);

    BoardResponseDto deleteBoard(Long boardId, User user);

    BoardWithCommentResponseDto getBoard(Long boardId);

    List<BoardWithoutCommentResponseDto> getBoards(String type);

    List<BoardWithoutCommentResponseDto> getBoardsAuth(String type, User user);
}
