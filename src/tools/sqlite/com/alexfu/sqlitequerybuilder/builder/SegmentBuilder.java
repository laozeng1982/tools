package tools.sqlite.com.alexfu.sqlitequerybuilder.builder;

import tools.sqlite.com.alexfu.sqlitequerybuilder.api.Builder;

public abstract class SegmentBuilder implements Builder {
  @Override
  public String toString() {
    return build();
  }
}
