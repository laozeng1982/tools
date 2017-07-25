package tools.sqlite.com.alexfu.sqlitequerybuilder.builder.delete;

import tools.sqlite.com.alexfu.sqlitequerybuilder.builder.SegmentBuilder;
import tools.sqlite.com.alexfu.sqlitequerybuilder.utils.Preconditions;
import tools.sqlite.com.alexfu.sqlitequerybuilder.utils.StringUtils;

public class DeleteBuilder extends SegmentBuilder {
  public DeleteFromBuilder from(String table) {
    Preconditions.checkArgument(table != null, "Table name cannot be null");
    return new DeleteFromBuilder(this, table);
  }

  @Override
  public String build() {
    return StringUtils.join(" ", "DELETE");
  }
}
