package com.example.jdbcexample.mappers.sqlMappers;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import javax.validation.constraints.NotNull;

public class ResultSetMapper<T> {

    @SuppressWarnings("unchecked")
    public List<T> mapResultSetToObject(@NotNull ResultSet resultSet, Class outputClass) {
        List<T> outputList = new ArrayList<T>();
        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            Field[] fields = outputClass.getDeclaredFields();

            while (resultSet.next()) {
                T bean = (T) outputClass.newInstance();
                for (int columnIndex = 1; columnIndex <= resultSetMetaData.getColumnCount(); columnIndex++) {

                    String columnName = resultSetMetaData.getColumnLabel(columnIndex);
                    Object columnValue = resultSet.getObject(columnIndex);

                    for (Field field : fields) {
                        if (field.isAnnotationPresent(Column.class)) {
                            Column column = field.getAnnotation(Column.class);

                            if (column.name().equalsIgnoreCase(columnName) && columnValue != null) {

                                BeanUtils.setProperty(bean, field.getName(), columnValue);
                                break;
                            }
                        }
                    }
                }
                outputList.add(bean);
            }

        } catch ( IllegalAccessException | InstantiationException | InvocationTargetException | SQLException e ) {
            e.printStackTrace();
        }
        return outputList;
    }
}
