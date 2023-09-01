package com.bemate.global.util;

import java.util.Collection;
import java.util.stream.Stream;

public class StreamUtil {
  public static <T> Stream<T> toStream(Collection<T> source) {
    if (source == null) {
      return Stream.empty();
    }

    return source.stream();
  }
}
