package com.budzko.cookbookspringboot.actuator.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.hal.Jackson2HalModule;
import org.springframework.hateoas.server.mvc.TypeConstrainedMappingJackson2HttpMessageConverter;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class Config {

    @Bean(name = "customRestTemplate")
    public RestTemplate customRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateProvider(restTemplateBuilder).getRestTemplate();
    }

    @Bean
    public RestTemplateProvider restTemplateProvider(RestTemplateBuilder restTemplateBuilder) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.setMessageConverters(this.getMessageConverters());
        restTemplate.setRequestFactory(this.getClientHttpRequestFactory());
        return new RestTemplateProvider(restTemplate);
    }

    private List<HttpMessageConverter<?>> getMessageConverters() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new Jackson2HalModule());
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(mapper);
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(
                List.of(
                        MediaType.APPLICATION_JSON,
                        MediaType.APPLICATION_OCTET_STREAM,
                        MediaType.MULTIPART_FORM_DATA,
                        MediaType.APPLICATION_FORM_URLENCODED,
                        MediaTypes.HAL_JSON,
                        MediaType.TEXT_HTML
                )
        );
        TypeConstrainedMappingJackson2HttpMessageConverter typeConstrainedMappingJackson2HttpMessageConverter
                = new TypeConstrainedMappingJackson2HttpMessageConverter(RepresentationModel.class);
        typeConstrainedMappingJackson2HttpMessageConverter.setObjectMapper(mapper);
        return List.of(
                typeConstrainedMappingJackson2HttpMessageConverter,
                new FormHttpMessageConverter(),
                new ResourceHttpMessageConverter(),
                mappingJackson2HttpMessageConverter,
                new ByteArrayHttpMessageConverter(),
                new StringHttpMessageConverter()
        );
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        return new HttpComponentsClientHttpRequestFactory(httpClientBuilder.build());
    }
}
