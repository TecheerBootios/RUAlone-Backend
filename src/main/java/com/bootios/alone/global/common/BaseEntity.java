package com.bootios.alone.global.common;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
@DynamicInsert
/** 모든 테이블의 공툥 요소인 BaseEntity 입니다. */
public abstract class BaseEntity {
  @CreatedDate
  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Column(name = "is_active", nullable = false)
  private Boolean isActive;

  @PrePersist
  public void prePersist() {
    this.isActive = this.isActive == null ? true : this.isActive;
  }

  protected void delete() {
    this.isActive = false;
  }
}
