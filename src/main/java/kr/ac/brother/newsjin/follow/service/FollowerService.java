package kr.ac.brother.newsjin.follow.service;

import java.util.List;
import kr.ac.brother.newsjin.follow.dto.response.FollowResponseDto;
import kr.ac.brother.newsjin.user.entity.User;

public interface FollowerService {

    List<FollowResponseDto> getFollowers(User user);

    void deleteFollower(Long followingId, User user);
}
