package com.bootios.alone.domain.post.domain.entity;

import com.bootios.alone.domain.users.domain.entity.Users;
import com.bootios.alone.global.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "post")
public class Post extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "creator_id", nullable = false)
  private Users creator;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "start_at", nullable = false)
  private LocalDateTime startAt;

  @Column(name = "limit_member", nullable = false)
  private Integer limitMember;

  @Column(name = "food_category", nullable = false) // 관리자 or 사용자 구분
  @Enumerated(EnumType.STRING)
  private FoodCategory foodCategory;

  @Builder
  public Post(
      Users creator,
      String title,
      LocalDateTime startAt,
      Integer limitMember,
      FoodCategory foodCategory) {
    this.creator = creator;
    this.title = title;
    this.startAt = startAt;
    this.limitMember = limitMember;
    this.foodCategory = foodCategory;
  }
}
