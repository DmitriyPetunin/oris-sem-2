package ru.kpfu.itis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kpfu.itis.config.ExchangeRateProperty;
import ru.kpfu.itis.dto.RateDTO;

@Service
@RequiredArgsConstructor
public class ExchangeRatesService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final ExchangeRateProperty exchangeRateProperty;
    public RateDTO getPrice(String param) throws JsonProcessingException {
        String url = String.format("https://openexchangerates.org/api/latest.json?app_id=%s&symbols=RUB,%s",
                exchangeRateProperty.getKey(), param);

        String response = restTemplate.getForObject(url, String.class);

        return objectMapper.readValue(response, RateDTO.class);
    }
}
