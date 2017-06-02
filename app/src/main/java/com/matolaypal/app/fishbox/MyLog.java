package com.matolaypal.app.fishbox;

class MyLog {
    static String getTag(Object o) {
        return o.getClass().getSimpleName() + " <#> ";
    }
}
