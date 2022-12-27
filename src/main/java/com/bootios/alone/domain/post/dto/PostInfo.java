package com.bootios.alone.domain.post.dto;

import com.bootios.alone.domain.post.domain.entity.FoodCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class PostInfo {

  private String title;

  private String chatUrl;
  private String creatorName;
  private LocalDateTime startAt;
  private Integer limitMember;
  private FoodCategory foodCategory;
  private LocalDateTime createdAt;
}
