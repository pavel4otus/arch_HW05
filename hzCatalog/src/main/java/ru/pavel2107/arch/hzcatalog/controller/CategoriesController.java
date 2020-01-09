package ru.pavel2107.arch.hzcatalog.controller;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.pavel2107.arch.hzcatalog.Utils;
import ru.pavel2107.arch.hzcatalog.domain.Brand;
import ru.pavel2107.arch.hzcatalog.domain.Category;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class CategoriesController  {

    private RestTemplate restTemplate;
    private HazelcastInstance hazelcastInstance;

    @Value("${app.categories.url}")
    private String url;

    @Value("${app.categories.ttl}")
    private Integer ttl;

    @Autowired
    public CategoriesController(RestTemplate restTemplate, @Qualifier("hazelcastInstance") HazelcastInstance hazelcastInstance) {
        this.restTemplate = restTemplate;
        this.hazelcastInstance = hazelcastInstance;
    }


    @GetMapping(value = "/microservices/v3/catalog/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public List <Category> getCategories(Principal principal, @RequestHeader("Authorization") String token) {
        IMap<Long, Category> map = hazelcastInstance.getMap("categories");
        List<Category> list = new ArrayList<>();
        if (map.entrySet().size() == 0) {
            HttpEntity<String> entity = Utils.initEntity("", token);
            ResponseEntity<List<Category>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Category>>() {
            });
            list = responseEntity.getBody();
            for (Category category : list) {
                map.put(category.getId(), category, ttl, TimeUnit.SECONDS);
            }
        }
        for (Map.Entry<Long, Category> pair : map.entrySet()) {
            list.add(pair.getValue());
        }

        return list;
    }

}