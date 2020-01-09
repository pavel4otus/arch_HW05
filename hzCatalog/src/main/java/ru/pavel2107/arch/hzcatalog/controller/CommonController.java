package ru.pavel2107.arch.hzcatalog.controller;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;
import ru.pavel2107.arch.hzcatalog.Utils;
import ru.pavel2107.arch.hzcatalog.domain.Brand;
import ru.pavel2107.arch.hzcatalog.domain.CommonTypeWithId;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;



public class CommonController<T extends CommonTypeWithId> {

    private RestTemplate restTemplate;
    private HazelcastInstance hazelcastInstance;

    @Autowired
    public CommonController(RestTemplate restTemplate, @Qualifier("hazelcastInstance") HazelcastInstance hazelcastInstance) {
        this.restTemplate = restTemplate;
        this.hazelcastInstance = hazelcastInstance;
    }

    /*
    public List<T> getList(String collectionName, String url, Integer ttl, Principal principal, @RequestHeader("Authorization") String token) {
        IMap<Long, T> map = hazelcastInstance.getMap(collectionName);
        List<T> list = new ArrayList<>();
        if( map.entrySet().size() == 0){
            HttpEntity<String> entity = Utils.initEntity( "", token);
            // ResponseEntity<List<T>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<T>>() {});
            ResponseEntity<Object[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Object[].class);
            list = responseEntity.getBody();
            for ( T element: list) {
                map.put( element.getId(), element, ttl, TimeUnit.SECONDS);
            }
        }
        for ( Map.Entry<Long, T> pair:map.entrySet()) {
            list.add( pair.getValue());
        }

        return list;
    }

*/

}


/*
public <T> Set<T> toResponseSet(Class<T[]> setType) {
    HttpEntity<?> body = new HttpEntity<>(objectBody, headers);
    ResponseEntity<T[]> response = template.exchange(url, method, body, setType);
    return Sets.newHashSet(response.getBody());
}
 */