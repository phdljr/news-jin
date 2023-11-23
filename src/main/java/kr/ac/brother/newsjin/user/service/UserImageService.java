package kr.ac.brother.newsjin.user.service;

import kr.ac.brother.newsjin.user.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserImageService {
    void uploadImage(MultipartFile multipartFile, User user);

    void deleteImage(User user);
}
