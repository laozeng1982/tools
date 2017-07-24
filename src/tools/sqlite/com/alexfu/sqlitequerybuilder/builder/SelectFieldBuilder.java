package tools.sqlite.com.alexfu.sqlitequerybuilder.builder;

import tools.sqlite.com.alexfu.sqlitequerybuilder.utils.StringUtils;

public class SelectFieldBuilder extends SelectBuilder {

  private String[] fields;

  public SelectFieldBuilder(String... fields) {
    this.fields = fields;
  }

  @Override
  public String build() {
    return StringUtils.join(" ", "SELECT", StringUtils.join(",", fields));
  }
}
