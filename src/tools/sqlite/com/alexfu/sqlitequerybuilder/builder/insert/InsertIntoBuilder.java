package tools.sqlite.com.alexfu.sqlitequerybuilder.builder.insert;

import tools.sqlite.com.alexfu.sqlitequerybuilder.builder.SegmentBuilder;
import tools.sqlite.com.alexfu.sqlitequerybuilder.utils.StringUtils;

import static tools.sqlite.com.alexfu.sqlitequerybuilder.utils.Preconditions.checkArgument;

public class InsertIntoBuilder extends SegmentBuilder {

    private final InsertBuilder predicate;
    private final String table;

    public InsertIntoBuilder(InsertBuilder predicate, String table) {
        this.predicate = predicate;
        this.table = table;
    }

    public InsertColumnsBuilder columns(String... columns) {
        checkArgument(columns != null, "Column names cannot be null");
        return new InsertColumnsBuilder(this, columns);
    }

    @Override
    public String build() {
        return StringUtils.join(" ", predicate.build(), table);
    }
}
