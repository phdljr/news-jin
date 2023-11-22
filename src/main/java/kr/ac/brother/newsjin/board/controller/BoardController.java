package kr.ac.brother.newsjin.board.controller;

import kr.ac.brother.newsjin.board.dto.request.BoardRequestDto;
import kr.ac.brother.newsjin.board.dto.response.BoardResponseDto;
import kr.ac.brother.newsjin.board.service.BoardService;
import kr.ac.brother.newsjin.global.security.userdetails.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/boards")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto boardRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.createBoard(boardRequestDto, userDetails.getUser());
    }

    @PutMapping("/boards/{boardId}")
    public ResponseEntity<BoardResponseDto> updateBoard(@PathVariable Long boardId, @RequestBody BoardRequestDto boardRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        BoardResponseDto boardResponseDto = boardService.updateBoard(boardId, boardRequestDto, userDetails.getUser());
        return ResponseEntity.ok(boardResponseDto);
    }

    @DeleteMapping("/boards/{boardId}")
    public ResponseEntity<BoardResponseDto> deleteBoard(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        BoardResponseDto boardResponseDto = boardService.deleteBoard(boardId, userDetails.getUser());
        return ResponseEntity.ok(boardResponseDto);
    }

}