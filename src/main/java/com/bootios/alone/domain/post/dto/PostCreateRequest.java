package com.bootios.alone.domain.post.dto;

import com.bootios.alone.domain.location.dto.LocationCreateDto;
import com.bootios.alone.domain.post.entity.FoodCategory;
import com.bootios.alone.domain.post.entity.PostType;
import java.time.LocalDateTime;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostCreateRequest {

  @NotNull(message = "userId는 공백이 올 수 없습니다.")
  private final String creatorEmail;

  @NotBlank(message = "제목은 빈칸일 수 없습니다")
  private final String title;

  @NotBlank(message = "채팅 URL은 빈칸일 수 없습니다")
  private final String chatUrl;

  private final LocalDateTime startAt;

  @Min(value = 1, message = "혼밥하실 수 없습니다.")
  @Max(value = 5, message = "너무 많이 먹지 마세요.")
  private final Integer limitMember;

  @NotBlank(message = "만남 장소는은 빈칸일 수 없습니다")
  private final String place;

  @NotNull(message = "음식 카테고리는 공백이 올 수 없습니다.")
  private final FoodCategory foodCategory;

  @NotNull(message = "게시글 타입은는 공백이 올 수 없습니다.")
  private final PostType postType;

  @NotNull(message = "게시자의 위치가 필요합니다.")
  private final LocationCreateDto location;
}
