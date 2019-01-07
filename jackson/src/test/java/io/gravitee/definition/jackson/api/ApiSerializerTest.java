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
package io.gravitee.definition.jackson.api;

import io.gravitee.definition.jackson.AbstractTest;
import io.gravitee.definition.model.Api;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author David BRASSELY (david.brassely at graviteesource.com)
 * @author GraviteeSource Team
 */
public class ApiSerializerTest extends AbstractTest {

    @Test
    public void definition_defaultHttpConfig() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-defaulthttpconfig.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_overridedHttpConfig() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-overridedhttpconfig.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_noPath() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-nopath.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_defaultPath() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-defaultpath.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_multiplePath() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-multiplepath.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_pathwithmethods() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-defaultpath.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_pathwithoutmethods() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-path-nohttpmethod.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_pathwithpolicies() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-defaultpath.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_pathwithpolicies_disabled() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-defaultpath.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_pathwithoutpolicy() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-path-withoutpolicy.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_apiWithoutProperties() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-withoutproperties.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_apiWithEmptyProperties() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-withemptyproperties.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_apiWithProperties() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-withproperties.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_withclientoptions() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-withclientoptions.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_withclientoptions_nossl() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-withclientoptions-nossl.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_withclientoptions_nooptions() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-withclientoptions-nooptions.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_withclientoptions_nooptions_defaultconfiguration() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-withclientoptions-nooptions.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_defaultLoadBalancer_roundRobin() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-defaulthttpconfig.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_no_failover() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-defaulthttpconfig.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_default_failover() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-default-failover.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_override_failover() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-override-failover.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_failover_singlecase() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-failover-singlecase.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_hostHeader_empty() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-empty-hostHeader.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_hostHeader() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-hostHeader.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_noCors() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-hostHeader.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_withCors_defaultValues() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-cors.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_multiTenants_enable() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-multitenants.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_withLogging_clientMode() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-logging-client.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_withclientoptions_truststore() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-withclientoptions-truststore.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_withclientoptions_keystore() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-withclientoptions-keystore.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }

    @Test
    public void definition_withHeaders() throws Exception {
        Api api = load("/io/gravitee/definition/jackson/api-headers.json", Api.class);

        String generatedJsonDefinition = objectMapper().writeValueAsString(api);
        Assert.assertNotNull(generatedJsonDefinition);
    }
}
