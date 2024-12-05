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
package io.americanexpress.service.sample.imperativebook.model;

import io.americanexpress.synapse.service.imperative.model.BaseServiceRequest;
import org.springframework.stereotype.Component;

/**
 * {@code ReadBookServiceRequest} class is the request model for {@link io.americanexpress.service.sample.imperativebook.service.ReadBookService}
 *
 * @author Francois Gutt
 */
@Component
public class ReadBookServiceRequest extends BaseBook implements BaseServiceRequest {

}