package kr.ac.brother.newsjin.follow.controller;

import java.util.List;
import kr.ac.brother.newsjin.follow.dto.response.FollowResponseDto;
import kr.ac.brother.newsjin.follow.service.FollowingService;
import kr.ac.brother.newsjin.global.security.userdetails.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class FollowingController {

    private final FollowingService followingService;

    @GetMapping("/following")
    public ResponseEntity<List<FollowResponseDto>> getFollowings(
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        List<FollowResponseDto> responseDto = followingService.getFollowings(userDetails.getUser());
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/following/{userId}")
    public ResponseEntity<String> followUser(
        @PathVariable Long userId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        followingService.followUser(userId, userDetails.getUser());
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/following/{userId}")
    public ResponseEntity<String> unfollowUser(
        @PathVariable Long userId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        followingService.unfollowUser(userId, userDetails.getUser());
        return ResponseEntity.ok("OK");
    }
}
