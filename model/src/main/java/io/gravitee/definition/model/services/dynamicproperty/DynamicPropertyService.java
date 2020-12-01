/**
 * Copyright (C) 2015 The Gravitee team (http://gravitee.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gravitee.definition.model.services.dynamicproperty;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.gravitee.definition.model.services.schedule.ScheduledService;

/**
 * @author David BRASSELY (david.brassely at graviteesource.com)
 * @author GraviteeSource Team
 */
public class DynamicPropertyService extends ScheduledService {

    public final static String SERVICE_KEY = "dynamic-property";

    private DynamicPropertyProvider provider;

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "provider")
    private DynamicPropertyProviderConfiguration configuration;

    public DynamicPropertyService() {
        super(SERVICE_KEY);
    }

    public DynamicPropertyProvider getProvider() {
        return provider;
    }

    public void setProvider(DynamicPropertyProvider provider) {
        this.provider = provider;
    }

    public DynamicPropertyProviderConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(DynamicPropertyProviderConfiguration configuration) {
        this.configuration = configuration;
    }

    public static String getServiceKey() {
        return SERVICE_KEY;
    }
}
