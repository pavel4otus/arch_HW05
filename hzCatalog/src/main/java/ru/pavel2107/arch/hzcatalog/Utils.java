package ru.pavel2107.arch.hzcatalog;

import com.hazelcast.core.IMap;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import ru.pavel2107.arch.hzcatalog.domain.Category;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Utils {
    public static HttpEntity initEntity( String request, String token){
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8);
        headers.setContentType(mediaType);
        headers.set( "Authorization", token);
        HttpEntity<String> entity = new HttpEntity<String>(request, headers);

        return entity;
    }


}


