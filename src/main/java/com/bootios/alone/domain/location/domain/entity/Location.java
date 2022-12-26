package com.bootios.alone.domain.location.domain.entity;

import com.bootios.alone.domain.post.domain.entity.Post;
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
  private Long id;

  @Column(nullable = false)
  private Float xCoordinate;

  @Column(nullable = false)
  private Float yCoordinate;

  @OneToOne(mappedBy = "location")
  private Post post;

  @Builder
  public Location(Float xCoordinate, Float yCoordinate) {
    this.xCoordinate = xCoordinate;
    this.yCoordinate = yCoordinate;
  }
}
