package kr.ac.brother.newsjin.global.security.userdetails;

import kr.ac.brother.newsjin.user.entity.User;
import kr.ac.brother.newsjin.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
            new UsernameNotFoundException("사용자를 찾을 수 없습니다.")
        );

        return new UserDetailsImpl(user);
    }
}