package com.namndt.springbootjooqupdate.repositories.utils;

import com.namndt.springbootjooqupdate.data.models.Order;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.SortField;
import org.jooq.impl.DSL;
import org.jooq.impl.TableRecordImpl;

import java.util.*;
import java.util.stream.Collectors;

public class PostgreUtils {

    public static List<SortField<Object>> buildSortQueries(List<Order> orders, Field<?>[] fields) {

        List<SortField<Object>> sortFieldList = new ArrayList<>();

        if (orders == null) {
            return sortFieldList;
        }

        Set<String> fieldNames = Arrays.stream(fields)
                .map(Field::getName)
                .collect(Collectors.toSet());

        for (Order order : orders) {
            if (fieldNames.contains(order.getField())) {
                if (order.getDirection().equals("asc")) {
                    sortFieldList.add(DSL.field(order.getField()).asc());
                } else {
                    sortFieldList.add(DSL.field(order.getField()).desc());
                }
            }
        }

        return sortFieldList;
    }

    public static <R extends TableRecordImpl<R>> Map<Field, Object> buildInsertQueries(R record, Object data) {
        record.from(data);
        Map<Field, Object> values = new HashMap<>();
        for (Field field : record.fields()) {
            if (record.getValue(field) != null) {
                values.put(field, record.getValue(field));
            }
        }
        return values;
    }
}
