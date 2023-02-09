package com.bootios.alone.domain.post.entity;

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

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "chat_url", nullable = false)
  private String chatUrl;

  @Column(name = "start_at", nullable = false)
  private LocalDateTime startAt;

  @Column(name = "limit_member", nullable = false)
  private Integer limitMember;

  @Column(name = "place", nullable = false)
  private String place;

  @Column(name = "food_category", nullable = false)
  @Enumerated(EnumType.STRING)
  private FoodCategory foodCategory;

  @Column(name = "post_type", nullable = false)
  @Enumerated(EnumType.STRING)
  private PostType postType;

  @Builder
  public Post(
      User creator,
      String title,
      String chatUrl,
      LocalDateTime startAt,
      Integer limitMember,
      String place,
      FoodCategory foodCategory,
      PostType postType) {
    this.creator = creator;
    this.title = title;
    this.chatUrl = chatUrl;
    this.startAt = startAt;
    this.limitMember = limitMember;
    this.place = place;
    this.foodCategory = foodCategory;
    this.postType = postType;
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
