package tools.sqlite.com.alexfu.sqlitequerybuilder.builder;

import tools.sqlite.com.alexfu.sqlitequerybuilder.api.Column;
import tools.sqlite.com.alexfu.sqlitequerybuilder.utils.Preconditions;
import tools.sqlite.com.alexfu.sqlitequerybuilder.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CreateTableSegmentBuilder extends SegmentBuilder {

    private boolean temp;
    private boolean ifNotExists;
    private String name;
    private final List<Column> definitions = new ArrayList<Column>();

    public CreateTableSegmentBuilder() {
    }

    public CreateTableSegmentBuilder temp() {
        temp = true;
        return this;
    }

    public CreateTableSegmentBuilder ifNotExists() {
        ifNotExists = true;
        return this;
    }

    public CreateTableSegmentBuilder column(List<Column> columns) {
        Preconditions.checkArgument(columns != null, "A non-null column is required.");
        for (int i = 0; i < columns.size(); i++) {
            definitions.add(columns.get(i));

        }
        return this;
    }

    /**
     *
     * @param column
     * @return
     */
    public CreateTableSegmentBuilder column(Column column) {
        Preconditions.checkArgument(column != null, "A non-null column is required.");

        definitions.add(column);

        return this;
    }

    public CreateTableSegmentBuilder table(String name) {
        Preconditions.checkNotEmpty(name, "Table name can not be empty.");
        this.name = name;
        return this;
    }

    @Override
    public String build() {
        String head = "CREATE "
                + (temp ? "TEMP " : "")
                + "TABLE"
                + (ifNotExists ? " IF NOT EXISTS" : "");

        String tail = name + "(" + StringUtils.join(",", definitions.toArray()) + ")";

        return StringUtils.join(" ", head, tail);
    }

}
