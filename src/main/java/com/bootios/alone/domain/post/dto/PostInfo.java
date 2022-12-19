package com.bootios.alone.domain.post.dto;

import com.bootios.alone.domain.post.domain.entity.FoodCategory;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class PostInfo {

  private String title;
  private String creatorName;
  private LocalDateTime startAt;
  private Integer limitMember;
  private FoodCategory foodCategory;
  private LocalDateTime createdAt;
  private String phoneNumber;
}
