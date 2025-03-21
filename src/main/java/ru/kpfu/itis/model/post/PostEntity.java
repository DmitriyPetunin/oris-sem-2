package ru.kpfu.itis.model.post;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


public class PostEntity {
    @JsonProperty("name")
    public String name;
    @JsonProperty("data")
    public Data data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    @JsonCreator
    public PostEntity(
            @JsonProperty("name") String name,
            @JsonProperty("data") Data data) {
        this.name = name;
        this.data = data;
    }

    public static class Data{
        @JsonProperty("year")
        public Integer year;
        @JsonProperty("price")
        public Integer price;
        @JsonProperty("CPU model")
        public String CPU;
        @JsonProperty("Hard disk size")
        public String memory;

        public Data(
                @JsonProperty("year") Integer year,
                @JsonProperty("price") Integer price,
                @JsonProperty("CPU model") String CPU,
                @JsonProperty("Hard disk size") String memory) {
            this.year = year;
            this.price = price;
            this.CPU = CPU;
            this.memory = memory;
        }

        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            this.year = year;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public String getCPU() {
            return CPU;
        }

        public void setCPU(String CPU) {
            this.CPU = CPU;
        }

        public String getMemory() {
            return memory;
        }

        public void setMemory(String memory) {
            this.memory = memory;
        }
    }

}
