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
package io.americanexpress.synapse.service.reactive.rest.controller;

import io.americanexpress.synapse.service.reactive.rest.model.BaseServiceRequest;
import io.americanexpress.synapse.service.reactive.rest.model.BaseServiceResponse;
import io.americanexpress.synapse.service.reactive.rest.service.BaseReadMonoReactiveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Mono;

/**
 * {@code BaseReadMonoReactiveController} class specifies the prototypes for listening for requests from the consumer
 * to Read (POST) a resource. This Controller expects only one object in request and one object in the
 * response, hence, "Mono" in the name.
 *
 * @param <I> an object extending the {@link BaseServiceRequest}
 * @param <O> an object extending the {@link BaseServiceResponse}
 * @param <S> an object extending the {@link BaseReadMonoReactiveService}
 * @author Gabriel Jimenez
 */
public abstract class BaseReadMonoReactiveController<I extends BaseServiceRequest, O extends BaseServiceResponse, S extends BaseReadMonoReactiveService<I, O>> extends BaseController<S> {

    /**
     * The constant INQUIRY_RESULTS.
     */
    public static final String INQUIRY_RESULTS = "/inquiry_results";

    /**
     * Get a single resource from the back end service.
     *
     * @param headers the headers
     * @param serviceRequest body from the consumer
     * @return a single resource from the back end service
     */
    @Operation(summary = "Reactive Read Mono", description = "Gets one resource")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
    })
    @PostMapping(INQUIRY_RESULTS)
    public Mono<ResponseEntity<O>> read(@RequestHeader HttpHeaders headers, @Valid @RequestBody I serviceRequest) {
        logger.entry(serviceRequest);

        var serviceResponse = service.read(headers, serviceRequest);
        var responseEntity = serviceResponse
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());

        logger.exit(responseEntity);
        return responseEntity;
    }
}
