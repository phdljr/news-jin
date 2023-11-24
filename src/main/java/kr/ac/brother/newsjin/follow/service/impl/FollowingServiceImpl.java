package kr.ac.brother.newsjin.follow.service.impl;

import java.util.List;
import java.util.Optional;
import kr.ac.brother.newsjin.follow.dto.response.FollowResponseDto;
import kr.ac.brother.newsjin.follow.dto.response.FollowResponseDto.FollowResponseDtoBuilder;
import kr.ac.brother.newsjin.follow.entity.Follow;
import kr.ac.brother.newsjin.follow.exception.AlreadyExistFollowingUserException;
import kr.ac.brother.newsjin.follow.exception.NotAllowFollowingYourselfException;
import kr.ac.brother.newsjin.follow.exception.NotFoundFollowingUserException;
import kr.ac.brother.newsjin.follow.repository.FollowRepository;
import kr.ac.brother.newsjin.follow.service.FollowingService;
import kr.ac.brother.newsjin.user.entity.User;
import kr.ac.brother.newsjin.user.exception.NotFoundUserException;
import kr.ac.brother.newsjin.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowingServiceImpl implements FollowingService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    // user가 userId를 팔로우 추가
    @Override
    public void followUser(final Long userId, final User user) {
        User followingUser = userRepository.findById(userId)
            .orElseThrow(NotFoundUserException::new);

        if(followingUser.getId().equals(user.getId())){
            throw new NotAllowFollowingYourselfException();
        }

        if(followRepository.existsByUserAndFollowingUser(user, followingUser)){
            throw new AlreadyExistFollowingUserException();
        }

        Follow follow = Follow.builder()
            .user(user)
            .followingUser(followingUser)
            .build();

        followRepository.save(follow);
    }

    // user가 userId를 팔로우 취소
    @Override
    public void unfollowUser(final Long userId, final User user) {
        User followingUser = userRepository.findById(userId)
            .orElseThrow(NotFoundUserException::new);

        Optional<Follow> findFollow =
            followRepository.findByUserAndFollowingUser(user, followingUser);

        if(findFollow.isEmpty()){
            throw new NotFoundFollowingUserException();
        }

        Follow follow = findFollow.get();

        followRepository.delete(follow);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FollowResponseDto> getFollowings(final User user) {
        return followRepository.findByUser(user)
            .stream()
            .map(follow -> FollowResponseDto.builder()
                .id(follow.getFollowingUser().getId())
                .username(follow.getFollowingUser().getUsername())
                .nickname(follow.getFollowingUser().getNickname())
                .build())
            .toList();
    }
}
