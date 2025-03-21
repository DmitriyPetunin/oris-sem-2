package ru.kpfu.itis.model.put;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PutEntityResponse {
    @JsonProperty("id")
    public String id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("data")
    public PutEntity.Data data;
    @JsonProperty("updatedAt")
    public String updatedAt;

}
