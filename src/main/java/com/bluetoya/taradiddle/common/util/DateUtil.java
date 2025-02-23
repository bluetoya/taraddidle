package com.bluetoya.taradiddle.common.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@UtilityClass
public class DateUtil {

  public LocalDateTime now() {
    return LocalDateTime.now(ZoneId.of("Asia/Seoul"));
  }

  public Date nowAsDate() {
    return Date.from(LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toInstant());
  }

  public Date getExpiration(int milliseconds) {
    return Date.from(
        LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toInstant().plusMillis(milliseconds));
  }
}
