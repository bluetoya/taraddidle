package com.bluetoya.taradiddle.common.util;

import com.bluetoya.taradiddle.common.constant.CommonConstant;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@UtilityClass
public class DateUtil {

  public LocalDateTime now() {
    return LocalDateTime.now(ZoneId.of(CommonConstant.REGION_ASIA_SEOUL));
  }

  public Date nowAsDate() {
    return Date.from(LocalDateTime.now().atZone(ZoneId.of(CommonConstant.REGION_ASIA_SEOUL)).toInstant());
  }

  public Date getExpiration(int milliseconds) {
    return Date.from(
        LocalDateTime.now().atZone(ZoneId.of(CommonConstant.REGION_ASIA_SEOUL)).toInstant().plusMillis(milliseconds));
  }
}
