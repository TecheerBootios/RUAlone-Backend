package com.bootios.alone.domain.post.domain.entity;

import com.bootios.alone.domain.location.domain.entity.Location;
import com.bootios.alone.domain.post.dto.PostUpdateRequest;
import com.bootios.alone.domain.user.entity.User;
import com.bootios.alone.global.common.BaseEntity;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "post")
public class Post extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "creator_id", nullable = false)
  private User creator;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id", nullable = true)
  private Location location;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "chat_url", nullable = false)
  private String chatUrl;

  @Column(name = "start_at", nullable = false)
  private LocalDateTime startAt;

  @Column(name = "limit_member", nullable = false)
  private Integer limitMember;

  @Column(name = "food_category", nullable = false)
  @Enumerated(EnumType.STRING)
  private FoodCategory foodCategory;

  @Builder
  public Post(
      User creator,
      Location location,
      String title,
      String chatUrl,
      LocalDateTime startAt,
      Integer limitMember,
      FoodCategory foodCategory) {
    this.creator = creator;
    this.location = location;
    this.title = title;
    this.chatUrl = chatUrl;
    this.startAt = startAt;
    this.limitMember = limitMember;
    this.foodCategory = foodCategory;
  }

  public void update(PostUpdateRequest postUpdateRequest) {
    this.title = postUpdateRequest.getTitle();
    this.startAt = postUpdateRequest.getStartAt();
    this.limitMember = postUpdateRequest.getLimitMember();
    this.foodCategory = postUpdateRequest.getFoodCategory();
  }

  public void deletePost() {
    this.delete();
  }
}
