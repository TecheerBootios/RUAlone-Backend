package com.bootios.alone.domain.post.dto;

import com.bootios.alone.domain.post.domain.entity.FoodCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class PostCreateRequest {

  private final Long creatorId;
  private final String title;
  private final LocalDateTime startAt;
  private final Integer limitMember;
  private final FoodCategory foodCategory;
}
