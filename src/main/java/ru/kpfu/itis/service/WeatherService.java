package ru.kpfu.itis.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kpfu.itis.model.post.PostEntity;
import ru.kpfu.itis.model.get.ProductResponse;
import ru.kpfu.itis.model.post.PostEntityResponse;
import ru.kpfu.itis.model.put.PutEntity;
import ru.kpfu.itis.model.put.PutEntityResponse;

import java.util.List;

@Service
public class WeatherService {
    private final RestTemplate restTemplate;
    private final String baseUrl = "https://api.restful-api.dev/";

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<ProductResponse> getAllObjects(){
        String url = "%s%s".formatted(baseUrl,"objects");


        String jsonResponse = restTemplate.getForObject(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(jsonResponse,new TypeReference<List<ProductResponse>>() {});
        } catch (Exception e){
            throw new RuntimeException("Error parsing JSON response", e);
        }
    }

    public String addObject(){
        PostEntity entity = new PostEntity("Apple MacBook Pro 14",new PostEntity.Data(2024,100,"M4 Pro","1 TB"));

        String url = "%s%s".formatted(baseUrl,"objects");

        ResponseEntity<PostEntityResponse> jsonResponse = restTemplate.postForEntity(url,entity,PostEntityResponse.class);

        PostEntityResponse productResponse = jsonResponse.getBody();
        System.out.println(productResponse.id);

        return productResponse.toString();
    }

    public String updateObject(String id){
        PutEntity entity = new PutEntity("Apple MacBook Pro 14",new PutEntity.Data(2024,1000,"M4 Pro","1 TB","black"));

        String url = "%s%s%s".formatted(baseUrl,"objects/",id);

        HttpEntity<PutEntity> requestEntity = new HttpEntity<>(entity);

        ResponseEntity<PutEntityResponse> jsonResponse = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                PutEntityResponse.class
        );

        PutEntityResponse productResponse = jsonResponse.getBody();

        return productResponse.toString();
    }

    public String delete(String id){
        String url = "%s%s%s".formatted(baseUrl,"objects/",id);

        restTemplate.delete(url);
        return "ура доделал орис, можно и кс регнуть";
    }
}
