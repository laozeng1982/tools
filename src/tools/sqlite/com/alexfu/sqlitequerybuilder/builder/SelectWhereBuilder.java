package tools.sqlite.com.alexfu.sqlitequerybuilder.builder;

import tools.sqlite.com.alexfu.sqlitequerybuilder.api.Builder;
import tools.sqlite.com.alexfu.sqlitequerybuilder.utils.Preconditions;

import static tools.sqlite.com.alexfu.sqlitequerybuilder.utils.StringUtils.join;

public class SelectWhereBuilder extends SegmentBuilder {

  private Builder prefix;
  private String condition;

  public SelectWhereBuilder(Builder prefix, String condition) {
    this.condition = condition;
    this.prefix = prefix;
  }

  public SelectAndBuilder and(String condition) {
    Preconditions.checkArgument(condition != null, "Condition cannot be null");
    return new SelectAndBuilder(this, condition);
  }

  public SelectLimitBuilder limit(int limit) {
    return new SelectLimitBuilder(this, limit);
  }

  public SelectOrderByBuilder orderBy(String column) {
    return new SelectOrderByBuilder(this, column);
  }

  @Override
  public String build() {
    return join(" ", prefix.build(), "WHERE", condition);
  }
}
