package tools.sqlite.com.alexfu.sqlitequerybuilder.builder;

import tools.sqlite.com.alexfu.sqlitequerybuilder.api.SelectType;
import tools.sqlite.com.alexfu.sqlitequerybuilder.utils.StringUtils;

public class SelectTypeBuilder extends SelectBuilder {

  private SelectType type;

  public SelectTypeBuilder(SelectType type) {
    this.type = type;
  }

  @Override
  public String build() {
    return StringUtils.join(" ", "SELECT", type.build());
  }
}
