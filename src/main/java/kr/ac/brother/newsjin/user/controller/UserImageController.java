package kr.ac.brother.newsjin.user.controller;

import kr.ac.brother.newsjin.global.security.userdetails.UserDetailsImpl;
import kr.ac.brother.newsjin.user.service.UserImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j(topic = "이미지 처리")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users/image")
public class UserImageController {

    private final UserImageService userImageService;

    @PostMapping
    public ResponseEntity<String> uploadImage(
        @RequestPart(name = "image") MultipartFile multipartFile,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        userImageService.uploadImage(multipartFile, userDetails.getUser());
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteImage(
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        userImageService.deleteImage(userDetails.getUser());
        return ResponseEntity.ok("OK");
    }
}
