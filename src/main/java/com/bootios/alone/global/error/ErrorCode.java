package com.bootios.alone.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** {주체}_{이유} message 는 동사 명사형으로 마무리 */
@Getter
@AllArgsConstructor
public enum ErrorCode {
  // Global
  INTERNAL_SERVER_ERROR(500, "G001", "서버 오류"),
  INPUT_INVALID_VALUE(400, "G002", "잘못된 입력"),

  // 예시
  // User 도메인
  EXAMPLE_USER_ERROR(400, "U001", "테스트용 예시 에러코드"),

  // Post
  NOT_FOUND_POST_ENTITY(400, "P001", "Post 를 찾을 수 없음"),
  ONLY_CREATOR_UPDATE_POST(403, "P002", "자신이 작성한 글만 수정할 수 있습니다."),

  // USER
  NOT_FOUND_USER_ENTITY(400, "U001", "User 를 찾을 수 없음"),
  ;

  private final int status;
  private final String code;
  private final String message;
}
