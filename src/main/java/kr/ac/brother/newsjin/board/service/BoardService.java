package kr.ac.brother.newsjin.board.service;

import kr.ac.brother.newsjin.board.dto.request.BoardRequestDto;
import kr.ac.brother.newsjin.board.dto.response.BoardResponseDto;
import kr.ac.brother.newsjin.user.entity.User;

public interface BoardService {

    BoardResponseDto createBoard(BoardRequestDto boardRequestDto, User user);

    BoardResponseDto updateBoard(Long boardId, BoardRequestDto boardRequestDto, User user);
}
