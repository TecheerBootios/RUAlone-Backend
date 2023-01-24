package com.bootios.alone.domain.post.dto;

import com.bootios.alone.domain.post.entity.FoodCategory;
import java.time.LocalDateTime;
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
  private final Long creatorId;

  @NotBlank(message = "제목은 빈칸일 수 없습니다")
  private final String title;

  @NotBlank(message = "채팅 URL은 빈칸일 수 없습니다")
  private final String chatUrl;

  private final LocalDateTime startAt;

  @Min(value = 2, message = "혼밥하실 수 없습니다.")
  private final Integer limitMember;

  @NotNull(message = "카테고리는 공백이 될 수 없습니다.")
  private final FoodCategory foodCategory;

  @Min(value = 0, message = "위도는 0이하가 될 수 없습니다.")
  @NotNull(message = "위도는 공백이 될 수 없습니다.")
  private final float latitude;

  @Min(value = 0, message = "경도는 0이하가 될 수 없습니다.")
  @NotNull(message = "경도는 공백이 될 수 없습니다.")
  private final float longitude;
}
