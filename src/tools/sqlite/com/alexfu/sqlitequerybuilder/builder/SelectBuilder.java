package tools.sqlite.com.alexfu.sqlitequerybuilder.builder;

import tools.sqlite.com.alexfu.sqlitequerybuilder.utils.Preconditions;

public abstract class SelectBuilder extends SegmentBuilder {
  public SelectFromBuilder from(String table) {
    Preconditions.checkArgument(table != null, "Table cannot be null");
    return new SelectFromBuilder(this, table);
  }
}
