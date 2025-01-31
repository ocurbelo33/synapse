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
package io.americanexpress.synapse.service.imperative.service;

import io.americanexpress.synapse.service.imperative.model.BaseServiceRequest;
import io.americanexpress.synapse.service.imperative.model.BaseServiceResponse;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import jakarta.validation.Valid;

/**
 * {@code BaseService} The base service every child controller should extend this parent service.
 * @author Francois Gutt
 */
public abstract class BaseService<
                 I extends BaseServiceRequest,
                 O extends BaseServiceResponse> {

    /**
     * Logger for the base service.
     */
    protected final XLogger logger = XLoggerFactory.getXLogger(getClass());

    /**
     * Execute service logic
     *
     * @param request received from the controller
     * @return response to the controller
     */
    public O execute(@Valid I request) {
        logger.entry(request);
        final var response = doExecute(request);
        logger.exit();
        return response;
    }

    protected abstract O doExecute(I request);
}
