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
package io.americanexpress.synapse.service.rest.service.reactive;

import io.americanexpress.synapse.service.rest.model.BaseServiceRequest;
import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import io.americanexpress.synapse.service.rest.service.BaseService;
import reactor.core.publisher.Flux;

/**
 * {@code BaseReadPolyReactiveService} class specifies the prototypes for performing business logic.
 * @param <I>
 * @param <O>
 */
public abstract class BaseReadPolyReactiveService<I extends BaseServiceRequest, O extends BaseServiceResponse> extends BaseService {

    /**
     * Retrieves multiple resources with a request body.
     * @param request
     * @return
     */
    public Flux<O> read(final I request) {
        logger.entry(request);

        final var response  = executeRead(request);

        logger.exit(response);

        return response;
    }

    protected abstract Flux<O> executeRead(I request);
}