package ru.kpfu.itis.model.post;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostEntityResponse {
    @JsonProperty("id")
    public String id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("data")
    public PostEntity.Data data;
    @JsonProperty("createdAt")
    public String createdAt;
}
