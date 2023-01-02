package com.bootios.alone.domain.user.service;

import com.bootios.alone.domain.user.dto.request.UserRequestDto;
import com.bootios.alone.domain.user.dto.response.UserResponseDto;
import com.bootios.alone.domain.user.entity.User;
import com.bootios.alone.domain.user.repository.UserRepository;
import com.bootios.alone.global.advice.exception.CUserNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {
  private UserRepository userRepository;

  @Transactional
  public Long save(UserRequestDto userDto) {
    User saved = userRepository.save(userDto.toEntity());
    return saved.getUserId();
  }

  @Transactional(readOnly = true)
  public UserResponseDto findById(Long id) {
    User user = userRepository.findById(id).orElseThrow(CUserNotFoundException::new);
    return new UserResponseDto(user);
  }

  @Transactional(readOnly = true)
  public UserResponseDto findByEmail(String email) {
    User user = userRepository.findByEmail(email).orElseThrow(CUserNotFoundException::new);
    return new UserResponseDto(user);
  }

  @Transactional(readOnly = true)
  public List<UserResponseDto> findAllUser() {
    return userRepository.findAll().stream().map(UserResponseDto::new).collect(Collectors.toList());
  }

  @Transactional
  public Long update(Long id, UserRequestDto userRequestDto) {
    User modifiedUser = userRepository.findById(id).orElseThrow(CUserNotFoundException::new);
    modifiedUser.updateNickName(userRequestDto.getNickName());
    return id;
  }

  @Transactional
  public void delete(Long id) {
    userRepository.deleteById(id);
  }
}
