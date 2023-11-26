package kr.ac.brother.newsjin.follow.service.impl;

import java.util.List;
import kr.ac.brother.newsjin.follow.dto.response.FollowResponseDto;
import kr.ac.brother.newsjin.follow.entity.Follow;
import kr.ac.brother.newsjin.follow.exception.NotFoundFollowingUserException;
import kr.ac.brother.newsjin.follow.repository.FollowRepository;
import kr.ac.brother.newsjin.follow.service.FollowerService;
import kr.ac.brother.newsjin.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowerServiceImpl implements FollowerService {

    private final FollowRepository followRepository;

    @Override
    @Transactional(readOnly = true)
    public List<FollowResponseDto> getFollowers(final User user) {
        return followRepository.findByFollowingUser(user)
            .stream()
            .map(follow -> FollowResponseDto.builder()
                .id(follow.getUser().getId())
                .username(follow.getUser().getUsername())
                .nickname(follow.getUser().getNickname())
                .build())
            .toList();
    }

    // 1번이 나를(2번) 팔로우하는걸 취소
    @Override
    public void deleteFollower(final Long followingId, final User user) {
        Follow follow = followRepository.findByUserIdAndFollowingUser(followingId, user)
            .orElseThrow(NotFoundFollowingUserException::new);

        followRepository.delete(follow);
    }
}
