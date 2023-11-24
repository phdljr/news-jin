package kr.ac.brother.newsjin.commentlike.controller;

import kr.ac.brother.newsjin.commentlike.service.CommentLikeService;
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
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    @PostMapping("/comments/{commentId}/like")
    public ResponseEntity<String> likeComment(
        @PathVariable("commentId") Long commentId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentLikeService.likeComment(commentId, userDetails.getUser());
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/comments/{commentId}/like")
    public ResponseEntity<String> unlikeComment(
        @PathVariable("commentId") Long commentId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentLikeService.unlikeComment
            (commentId, userDetails.getUser());
        return ResponseEntity.ok("OK");
    }
}
