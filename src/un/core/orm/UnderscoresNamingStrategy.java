package un.core.orm;

import org.hibernate.cfg.ImprovedNamingStrategy;

/**
 * Created by admin on 2018/4/27.
 */
public class UnderscoresNamingStrategy extends ImprovedNamingStrategy {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public String columnName(String columnName) {
        return addUnderscores(columnName).toUpperCase();
    }

    @Override
    public String tableName(String tableName) {
        return addUnderscores(tableName).toLowerCase();
    }

    @Override
    public String classToTableName(String className) {
        return tableName(className);
    }

    @Override
    public String propertyToColumnName(String propertyName) {
        return addUnderscores(propertyName).toUpperCase();
    }

}
