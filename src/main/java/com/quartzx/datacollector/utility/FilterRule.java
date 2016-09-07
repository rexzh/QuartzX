package com.quartzx.datacollector.utility;

/**
 * Created by zling on 9/7/2016.
 */
public enum FilterRule {
    None("none"),
    KeepMin("keepmin"),
    KeepMax("keepmax");

    public String getCode() {
        return code;
    }

    private final String code;

    FilterRule(String code) {
        this.code = code;
    }

    public static FilterRule fromCode(String code) {
        for (FilterRule rule : FilterRule.values()) {
            if (rule.code.equals(code))
                return rule;
        }
        return null;
    }
}
