package com.sparta.blog.service;

import com.sparta.blog.models.SignupRequestDto;
import com.sparta.blog.models.User;
import com.sparta.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;


@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(SignupRequestDto requestDto) {
        // 회원 ID 요구 조건 검사
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();

        String tmpPw = requestDto.getPassword();
        String tmpPw2 = requestDto.getPassword2();

        validationCheck(username, email, tmpPw, tmpPw2);

        // 패스워드 암호화

        User user = new User(username, password, email);
        userRepository.save(user);
        return user;
    }

    private void validationCheck(String username, String email, String tmpPw, String tmpPw2) {
        if(!Pattern.matches("^[A-Za-z0-9]{3,}$", username)){
            throw new IllegalArgumentException("최소 3자 이상 입력해주세요. 알파벳과 숫자만 사용해주세요.");
        }
        // 회원 ID 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        found = userRepository.findByEmail(email);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 이메일이 존재합니다.");
        }

        if(!tmpPw.equals(tmpPw2)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        if(tmpPw.contains(username)){
            throw new IllegalArgumentException("비밀번호가 id를 포함하고 있습니다.");
        }
    }
}