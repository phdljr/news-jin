package kr.ac.brother.newsjin.follow.service;

import java.util.List;
import kr.ac.brother.newsjin.follow.dto.response.FollowResponseDto;
import kr.ac.brother.newsjin.user.entity.User;

public interface FollowingService {

    void followUser(Long userId, User user);

    void unfollowUser(Long userId, User user);

    List<FollowResponseDto> getFollowings(User user);
}
