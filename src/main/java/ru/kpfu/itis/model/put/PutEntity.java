package ru.kpfu.itis.model.put;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PutEntity {
    @JsonProperty("name")
    public String name;
    @JsonProperty("data")
    public PutEntity.Data data;

    @JsonCreator
    public PutEntity(
            @JsonProperty("name")String name,
            @JsonProperty("data")PutEntity.Data data
    ) {
        this.name = name;
        this.data = data;
    }

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

    public static class Data{
        @JsonProperty("year")
        public Integer year;
        @JsonProperty("price")
        public Integer price;
        @JsonProperty("CPU model")
        public String CPU;
        @JsonProperty("Hard disk size")
        public String memory;
        @JsonProperty("color")
        public String color;

        public Data(
                @JsonProperty("year") Integer year,
                @JsonProperty("price") Integer price,
                @JsonProperty("CPU model")String CPU,
                @JsonProperty("Hard disk size") String memory,
                @JsonProperty("color") String color) {
            this.year = year;
            this.price = price;
            this.CPU = CPU;
            this.memory = memory;
            this.color = color;
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

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }
}
