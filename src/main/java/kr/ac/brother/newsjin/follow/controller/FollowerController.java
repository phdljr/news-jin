package kr.ac.brother.newsjin.follow.controller;

import java.util.List;
import kr.ac.brother.newsjin.follow.dto.response.FollowResponseDto;
import kr.ac.brother.newsjin.follow.service.FollowerService;
import kr.ac.brother.newsjin.global.security.userdetails.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class FollowerController {

    private final FollowerService followerService;

    @GetMapping("/follower")
    public ResponseEntity<List<FollowResponseDto>> getFollowers(
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        List<FollowResponseDto> responseDto = followerService.getFollowers(userDetails.getUser());
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/follower/{followingId}")
    public ResponseEntity<String> deleteFollower(
        @PathVariable("followingId") Long followingId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        followerService.deleteFollower(followingId, userDetails.getUser());
        return ResponseEntity.ok("OK");
    }
}
