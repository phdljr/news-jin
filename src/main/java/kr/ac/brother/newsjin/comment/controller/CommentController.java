package kr.ac.brother.newsjin.comment.controller;

import kr.ac.brother.newsjin.board.entity.Board;
import kr.ac.brother.newsjin.comment.dto.request.CommentRequestDTO;
import kr.ac.brother.newsjin.comment.dto.response.CommentResponseDTO;
import kr.ac.brother.newsjin.comment.service.CommentService;
import kr.ac.brother.newsjin.global.security.userdetails.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CommentController {
    // CommentService 인스턴스를 주입받음 (생성자 주입)
    private final CommentService commentService;

    // 댓글 생성 요청을 처리하는 메서드
    @PostMapping("/comments/{boardId}")
    public CommentResponseDTO createComment(@RequestBody CommentRequestDTO commentRequestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long boardId) {

        // CommentService 의 createComment 메서드를 호출하여 댓글을 생성하고 결과를 반환
        return commentService.createComment(commentRequestDTO, userDetails.getUser(), boardId);
    }
}
