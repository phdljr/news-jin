package kr.ac.brother.newsjin.board.controller;

import java.util.List;
import kr.ac.brother.newsjin.board.dto.request.BoardRequestDto;
import kr.ac.brother.newsjin.board.dto.response.BoardResponseDto;
import kr.ac.brother.newsjin.board.dto.response.BoardWithCommentResponseDto;
import kr.ac.brother.newsjin.board.dto.response.BoardWithoutCommentResponseDto;
import kr.ac.brother.newsjin.board.service.BoardService;
import kr.ac.brother.newsjin.global.security.userdetails.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/boards")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto boardRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.createBoard(boardRequestDto, userDetails.getUser());
    }

    @PutMapping("/boards/{boardId}")
    public ResponseEntity<BoardResponseDto> updateBoard(@PathVariable Long boardId,
        @RequestBody BoardRequestDto boardRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        BoardResponseDto boardResponseDto = boardService.updateBoard(boardId, boardRequestDto,
            userDetails.getUser());
        return ResponseEntity.ok(boardResponseDto);
    }

    @DeleteMapping("/boards/{boardId}")
    public ResponseEntity<BoardResponseDto> deleteBoard(@PathVariable Long boardId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        BoardResponseDto boardResponseDto = boardService.deleteBoard(boardId,
            userDetails.getUser());
        return ResponseEntity.ok(boardResponseDto);
    }

    @GetMapping("/boards/{boardId}")
    public ResponseEntity<BoardWithCommentResponseDto> getBoard(@PathVariable Long boardId) {
        BoardWithCommentResponseDto boardWithCommentResponseDto = boardService.getBoard(boardId);
        return ResponseEntity.ok(boardWithCommentResponseDto);
    }

    @GetMapping("/boards")
    public ResponseEntity<List<BoardWithoutCommentResponseDto>> getBoards(
        @RequestParam("type") String type
    ) {
        List<BoardWithoutCommentResponseDto> boardWithoutCommentResponseDto =
            boardService.getBoards(type);
        return ResponseEntity.ok(boardWithoutCommentResponseDto);
    }

    @GetMapping("/boards/auth")
    public ResponseEntity<List<BoardWithoutCommentResponseDto>> getBoardsAuth(
        @RequestParam("type") String type,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        List<BoardWithoutCommentResponseDto> boardWithoutCommentResponseDto =
            boardService.getBoardsAuth(type, userDetails.getUser());
        return ResponseEntity.ok(boardWithoutCommentResponseDto);
    }

}