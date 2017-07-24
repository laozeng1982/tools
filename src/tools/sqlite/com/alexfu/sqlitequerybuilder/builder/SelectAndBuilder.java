package tools.sqlite.com.alexfu.sqlitequerybuilder.builder;

import tools.sqlite.com.alexfu.sqlitequerybuilder.api.Builder;
import tools.sqlite.com.alexfu.sqlitequerybuilder.utils.StringUtils;

public class SelectAndBuilder extends SegmentBuilder {

  private Builder prefix;
  private String condition;

  public SelectAndBuilder(Builder prefix, String condition) {
    this.prefix = prefix;
    this.condition = condition;
  }

  public SelectAndBuilder and(String condition) {
    return new SelectAndBuilder(this, condition);
  }

  @Override
  public String build() {
    return StringUtils.join(" ", prefix.build(), "AND", condition);
  }
}
