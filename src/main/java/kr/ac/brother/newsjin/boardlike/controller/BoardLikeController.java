package kr.ac.brother.newsjin.boardlike.controller;

import kr.ac.brother.newsjin.boardlike.service.BoardLikeService;
import kr.ac.brother.newsjin.global.security.userdetails.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class BoardLikeController {

    private final BoardLikeService boardLikeService;

    @PostMapping("/boards/{boardId}/like")
    public ResponseEntity<String> likeBoard(
        @PathVariable("boardId") Long boardId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        boardLikeService.likeBoard(boardId, userDetails.getUser());
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/boards/{boardId}/like")
    public ResponseEntity<String> unlikeBoard(
        @PathVariable("boardId") Long boardId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        boardLikeService.unlikeBoard(boardId, userDetails.getUser());
        return ResponseEntity.ok("OK");
    }
}
