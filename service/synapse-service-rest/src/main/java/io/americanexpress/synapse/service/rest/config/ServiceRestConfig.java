/*
 * Copyright 2020 American Express Travel Related Services Company, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.americanexpress.synapse.service.rest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
//import io.americanexpress.synapse.framework.api.docs.ApiDocsConfig;
import io.americanexpress.synapse.framework.exception.config.ExceptionConfig;
import io.americanexpress.synapse.service.rest.interceptor.MetricInterceptor;
import io.americanexpress.synapse.utilities.common.config.UtilitiesCommonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;
import java.util.List;

/**
 * {@code ServiceRestConfig} class sets common configurations for the service layer.
 *
 * @author Gabriel Jimenez
 */
@Configuration
@ComponentScan(basePackages = "io.americanexpress.synapse.service.rest")
//@Import({ExceptionConfig.class, ApiDocsConfig.class, UtilitiesCommonConfig.class})
@Import({ExceptionConfig.class, UtilitiesCommonConfig.class})
public class ServiceRestConfig implements WebMvcConfigurer {

    /**
     * Default object mapper.
     */
    private final ObjectMapper defaultObjectMapper;

    /**
     * Default Metric Interceptor.
     */
    protected final MetricInterceptor interceptor;

    /**
     * Constructor taking in objectMapper & metricInterceptor
     * @param defaultObjectMapper   the default object mapper.
     * @param interceptor           the metric interceptor.
     */
    @Autowired
    public ServiceRestConfig(ObjectMapper defaultObjectMapper, MetricInterceptor interceptor) {
        this.defaultObjectMapper = defaultObjectMapper;
        this.interceptor = interceptor;
    }

    /**
     * Get the JSON message converter.
     *
     * @return the JSON message converter.
     */
    @Bean
    public MappingJackson2HttpMessageConverter jsonMessageConverter() {
        final MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter(getObjectMapper());
        messageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        return messageConverter;
    }

    /**
     * Configure the message converters.
     *
     * @param converters message converters of the application.
     */
    @Override
    public void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
        converters.add(jsonMessageConverter());
    }

    /**
     * Adds interceptor to the registry.
     * @param registry interceptor registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/**");
    }

    /**
     * Returns default objectMapper.
     * @return an object mapper
     */
    protected ObjectMapper getObjectMapper() {
        return defaultObjectMapper;
    }
}
