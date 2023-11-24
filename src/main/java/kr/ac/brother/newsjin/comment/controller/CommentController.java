package kr.ac.brother.newsjin.comment.controller;

import kr.ac.brother.newsjin.comment.dto.request.CommentRequestDTO;
import kr.ac.brother.newsjin.comment.dto.response.CommentResponseDTO;
import kr.ac.brother.newsjin.comment.service.CommentService;
import kr.ac.brother.newsjin.global.security.userdetails.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    // 댓글 수정 요청 처리 메서드
    // 댓글 수정 시 사용자의 변경내용, 유저 정보, 게시판 id, 댓글 id 를 받아옴
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponseDTO> modifyComment(@RequestBody CommentRequestDTO commentRequestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentId) {
        CommentResponseDTO commentResponseDTO = commentService.modifyComment(commentRequestDTO, userDetails.getUser(), commentId);
        // 수정된 댓글 정보를 포함하여 CommentResponseDTO 를 HTTP 응답으로 반환
        return ResponseEntity.ok(commentResponseDTO);
    }

    // 댓글 삭제 요청
    // 삭제 시 사용자가 보내는 request(요청) 이 없으니 사용자 정보와 댓글 id만 가져옴
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponseDTO> deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentId) {
        // deleteComment 메서드를 사용하여 댓글을 삭제하고, 삭제된 댓글에 대한 정보를 CommentResponseDTO 형태로 받아옴 | deleteComment 메서드는 CommentService 에 있고 실행내용은 CommentServiceImpl 에 구현
        CommentResponseDTO commentResponseDTO = commentService.deleteComment(userDetails.getUser(), commentId);
        // 받아온 CommentResponseDTO 정보를 사용자에게 반환
        return ResponseEntity.ok(commentResponseDTO);
    }
}
