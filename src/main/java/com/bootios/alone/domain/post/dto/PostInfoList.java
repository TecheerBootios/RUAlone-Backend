package com.bootios.alone.domain.post.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class PostInfoList {
  List<PostInfo> postInfos = new ArrayList<>();
}
