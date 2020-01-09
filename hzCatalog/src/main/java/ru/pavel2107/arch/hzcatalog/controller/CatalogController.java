package ru.pavel2107.arch.hzcatalog.controller;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.pavel2107.arch.hzcatalog.Utils;
import ru.pavel2107.arch.hzcatalog.domain.Brand;
import ru.pavel2107.arch.hzcatalog.domain.User;
import ru.pavel2107.arch.hzcatalog.dto.GoodDto;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class CatalogController {

    private RestTemplate restTemplate;
    private HazelcastInstance hazelcastInstance;

    @Value( "${app.catalog.url}")
    private String url;

    @Value( "${app.catalog.ttl}")
    private Integer ttl;

    @Autowired
    public CatalogController( RestTemplate restTemplate, @Qualifier("hazelcastInstance") HazelcastInstance hazelcastInstance) {
        this.restTemplate = restTemplate;
        this.hazelcastInstance = hazelcastInstance;
    }

    @GetMapping(value = "/microservices/v3/catalog", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GoodDto> listGoods(Principal principal, @RequestHeader("Authorization") String token) {
        List<GoodDto> list = listGoods(principal, token, url, "", ttl, null);
        return list;
    }

    @GetMapping(value = "/microservices/v3/catalog/bycategory", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GoodDto> listGoodsByCategory(Principal principal, @RequestHeader("Authorization") String token, @RequestParam Long categoryId) {
        Predicate predicate = Predicates.equal( "category", categoryId);
        List<GoodDto> list = listGoods(principal, token, url, "" + categoryId, ttl, predicate);
        return list;
    }

    @GetMapping(value = "/microservices/v3/catalog/bybrand", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GoodDto> listGoodsByBrand(Principal principal, @RequestHeader("Authorization") String token, @RequestParam Long brandId) {
        Predicate predicate = Predicates.equal( "brand", brandId);
        List<GoodDto> list = listGoods(principal, token, url, "", ttl, predicate);
        return list;
    }

    @GetMapping(value = "/microservices/v3/catalog/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GoodDto get(Principal principal, @RequestHeader("Authorization") String token, @PathVariable(value = "id") Long id) {
        Predicate predicate = Predicates.equal( "id", id);
        List<GoodDto> list = listGoods(principal, token, url, "", ttl, predicate);
        GoodDto good = null;
        if( list != null && list.size() == 1) good = list.get( 0);
        return good;
    }


    public List<GoodDto> listGoods(Principal principal, @RequestHeader("Authorization") String token, String url, String request, Integer ttl, Predicate p) {
        IMap<Long, GoodDto> map = hazelcastInstance.getMap("goods");
        List<GoodDto> list;
        System.out.println( "Запускаем поиск значений");
        if( map.entrySet().size() == 0){
            System.out.println( "Кеш пуст. Делаем запрос в базу данных");
            HttpEntity<String> entity = Utils.initEntity( request, token);
            ResponseEntity<List<GoodDto>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<GoodDto>>() {});
            list = responseEntity.getBody();
            System.out.println( "Получено " + list.size() + " значений");
            for ( GoodDto good: list) {
                map.put( good.getId(), good, ttl, TimeUnit.SECONDS);
            }
            System.out.println( "Кеш заполнен");
            list.clear();
        } else {
            System.out.println("В кеше есть данные. Возвращаем данные из кеша");
        }
        Collection<GoodDto> collection = p == null ? map.values() : map.values(p);
        list = new ArrayList<>( collection);
        System.out.println( "Получено " + list.size() + " значений из кеша");
        System.out.println( "Поиск завершен");
        return list;
    }



}
