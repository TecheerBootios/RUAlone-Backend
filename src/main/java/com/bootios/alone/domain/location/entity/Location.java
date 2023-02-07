package com.bootios.alone.domain.location.entity;

import com.bootios.alone.domain.location.dto.LocationUpdateRequest;
import com.bootios.alone.domain.post.dto.PostUpdateRequest;
import com.bootios.alone.domain.post.entity.Post;
import com.bootios.alone.global.common.BaseEntity;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "location")
public class Location extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "location_id")
  private Long id;

  @Column(nullable = false)
  private Float latitude;

  @Column(nullable = false)
  private Float longitude;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;

  @Builder
  public Location(Float latitude, Float longitude, Post post) {
    this.post = post;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public void update(LocationUpdateRequest locationUpdateRequest) {
    this.latitude = locationUpdateRequest.getLatitude();
    this.longitude = locationUpdateRequest.getLongitude();
  }

}
