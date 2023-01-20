package com.qjj.domain.model;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum FacilityType {
    TRUCK("Truck"), PUSH_CART("Push Cart"), NONE("");
    String type;

    private static final Map<String,FacilityType> Type_Map;

    FacilityType(String type) {
        this.type = type;
    }
    static {
        Map<String,FacilityType> map = new ConcurrentHashMap<String, FacilityType>();
        for (FacilityType type : FacilityType.values()) {
            map.put(type.type ,type);
        }
        Type_Map = Collections.unmodifiableMap(map);
    }

    public static FacilityType get (String name) {
        return Type_Map.get(name);
    }
}
