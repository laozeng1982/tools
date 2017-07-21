package tools.sqlite.com.alexfu.sqlitequerybuilder.builder;

import tools.sqlite.com.alexfu.sqlitequerybuilder.api.Builder;
import tools.sqlite.com.alexfu.sqlitequerybuilder.utils.Preconditions;

import static tools.sqlite.com.alexfu.sqlitequerybuilder.utils.StringUtils.join;

public class SelectJoinBuilder extends SegmentBuilder {

  private Builder prefix;
  private String table;
  private JoinType joinType;

  public SelectJoinBuilder(Builder prefix, String table, JoinType joinType) {
    this.prefix = prefix;
    this.table = table;
    this.joinType = joinType;
  }

  public JoinOnBuilder on(String condition) {
    Preconditions.checkArgument(condition != null, "Condition cannot be null");
    return new JoinOnBuilder(this, condition);
  }

  @Override
  public String build() {
    return join(" ", prefix.build(), joinType.toSQL(), table);
  }
}
