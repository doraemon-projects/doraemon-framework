package org.doraemon.framework.core.base.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * created with IntelliJ IDEA.
 * description: 排序实体
 * author:      fengwenping
 * date:        2019/7/14 19:18
 */
public class Sort implements Serializable {

    public static final Direction DEFAULT_DIRECTION = Direction.ASC;

    private final List<Order> orders;

    public Sort(Order... orders) {
        this(Arrays.asList(orders));
    }

    public Sort(List<Order> orders) {
        if (null == orders || orders.isEmpty()) {
            throw new IllegalArgumentException("You have to provide at least one sort property to sort by!");
        }

        this.orders = orders;
    }

    public Sort(String... properties) {
        this(DEFAULT_DIRECTION, properties);
    }

    public Sort(Direction direction, String... properties) {
        this(direction, properties == null ? new ArrayList<>() : Arrays.asList(properties));
    }

    public Sort(Direction direction, List<String> properties) {

        if (properties == null || properties.isEmpty()) {
            throw new IllegalArgumentException("You have to provide at least one property to sort by!");
        }

        this.orders = new ArrayList<>(properties.size());

        for (String property : properties) {
            this.orders.add(new Order(direction, property));
        }
    }

    public enum Direction {
        ASC, DESC;

        public boolean isAscending() {
            return this.equals(ASC);
        }

        public boolean isDescending() {
            return this.equals(DESC);
        }

        public static Direction fromString(String value) {
            try {
                return Direction.valueOf(value.toUpperCase());
            } catch (Exception e) {
                throw new IllegalArgumentException(String.format("Invalid value '%s' for orders given! Has to be either 'desc' or 'asc' (case insensitive).", value), e);
            }
        }
    }

    public static class Order implements Serializable {

        private final Direction direction;
        private final String property;

        public Order(String property) {
            this(DEFAULT_DIRECTION, property);
        }

        private Order(Direction direction, String property) {
            if (property == null || property.equals("")) {
                throw new IllegalArgumentException("Property must not null or empty!");
            }
            this.direction = direction == null ? DEFAULT_DIRECTION : direction;
            this.property = property;
        }

        public Direction getDirection() {
            return direction;
        }

        public String getProperty() {
            return property;
        }
    }
}
