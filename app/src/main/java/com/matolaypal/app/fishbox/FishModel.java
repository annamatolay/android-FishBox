package com.matolaypal.app.fishbox;

import io.realm.RealmObject;
import io.realm.annotations.Required;

class FishModel extends RealmObject {
    @Required
    private String name;

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FishModel{" +
                "name='" + name + '\'' +
                '}';
    }
}
