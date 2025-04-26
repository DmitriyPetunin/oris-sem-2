package ru.kpfu.itis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RateDTO {
    @JsonProperty("rates")
    private Rate rates;
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Rate {
        @JsonProperty("RUB")
        private String rub;

    }
}
