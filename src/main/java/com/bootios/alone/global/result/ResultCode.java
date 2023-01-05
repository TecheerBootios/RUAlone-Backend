package com.bootios.alone.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** {행위}_{목적어}_{성공여부} message 는 동사 명사형으로 마무리 */
@Getter
@AllArgsConstructor
public enum ResultCode {

  // 도메인 별로 나눠서 관리(ex: User 도메인)
  // user

  // post
  CREATE_POST_SUCCESS("P001", "POST 생성 성공"),
  UPDATE_POST_SUCCESS("P002", "POST 수정 성공"),

  DELETE_POST_SUCCESS("P003", "POST 삭제 성공"),

  GET_ONE_POST_SUCCESS("P004", "POST 단일 조회 성공"),
  GET_POST_PAGINATION_SUCCESS("P005", "POST PAGINATION 조회 성공"),

  ;

  private final String code;
  private final String message;
}
