package ru.userpetproject.utils;

import lombok.experimental.UtilityClass;
import net.ttddyy.dsproxy.QueryCount;
import net.ttddyy.dsproxy.listener.SingleQueryCountHolder;

@UtilityClass
public class QueryCountService {

    static final SingleQueryCountHolder QUERY_COUNT_HOLDER = new SingleQueryCountHolder();

    public static void clear() {
        final var map = QUERY_COUNT_HOLDER.getQueryCountMap();
        map.put("", new QueryCount());
    }

    public static QueryCount get() {
        final var map = QUERY_COUNT_HOLDER.getQueryCountMap();
        return map.get("");
    }
}
