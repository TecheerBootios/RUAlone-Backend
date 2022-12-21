package com.bootios.alone.domain.post.dto;

import com.bootios.alone.domain.post.domain.entity.FoodCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class PostUpdateRequest {

  @NotNull(message = "수정할 postId는 공백이 올 수 없습니다.")
  private final Long postId;

  @NotNull(message = "userId는 공백이 올 수 없습니다.")
  private final Long creatorId;

  @NotBlank(message = "제목은 빈칸일 수 없습니다")
  private final String title;

  @Future(message = "날짜는 미래만 고를 수 있습니다.")
  private final LocalDateTime startAt;

  @Min(value = 2, message = "혼밥하실 수 없습니다.")
  private final Integer limitMember;

  @NotNull(message = "카테고리는 공백이 될 수 없습니다.")
  private final FoodCategory foodCategory;
}
