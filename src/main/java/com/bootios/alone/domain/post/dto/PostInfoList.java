package com.bootios.alone.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class PostInfoList {
  List<PostInfo> postInfos =  new ArrayList<>();
}
