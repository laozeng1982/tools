package tools.sqlite.com.alexfu.sqlitequerybuilder.builder.delete;

import tools.sqlite.com.alexfu.sqlitequerybuilder.api.Builder;
import tools.sqlite.com.alexfu.sqlitequerybuilder.builder.SegmentBuilder;

import static tools.sqlite.com.alexfu.sqlitequerybuilder.utils.StringUtils.join;

public class DeleteWhereBuilder extends SegmentBuilder {

    private Builder prefix;
    private String condition;

    public DeleteWhereBuilder(Builder prefix, String condition) {
        this.condition = condition;
        this.prefix = prefix;
    }

    @Override
    public String build() {
        return join(" ", prefix.build(), "WHERE", condition);
    }
}
