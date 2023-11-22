package kr.ac.brother.newsjin.board.service.impl;

import kr.ac.brother.newsjin.board.dto.request.BoardRequestDto;
import kr.ac.brother.newsjin.board.dto.response.BoardResponseDto;
import kr.ac.brother.newsjin.board.entity.Board;
import kr.ac.brother.newsjin.board.repository.BoardRepository;
import kr.ac.brother.newsjin.board.service.BoardService;
import kr.ac.brother.newsjin.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto, User user) {
        Board board = Board.builder()
                .title(boardRequestDto.getTitle())
                .content(boardRequestDto.getContent())
                .user(user)
                .build();

        board = boardRepository.save(board);

        return new BoardResponseDto(board);
    }

}
