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
package io.gravitee.definition.model.endpoint;

import com.fasterxml.jackson.annotation.*;
import io.gravitee.common.http.HttpHeaders;
import io.gravitee.definition.model.*;
import io.gravitee.definition.model.services.healthcheck.EndpointHealthCheckService;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author David BRASSELY (david.brassely at graviteesource.com)
 * @author GraviteeSource Team
 */
public class HttpEndpoint extends Endpoint {

    @JsonProperty("proxy")
    private HttpProxy httpProxy;

    @JsonProperty("http")
    private HttpClientOptions httpClientOptions = new HttpClientOptions();

    @JsonProperty("ssl")
    private HttpClientSslOptions httpClientSslOptions;

    private Map<String, String> headers;

    @JsonProperty("healthcheck")
    private EndpointHealthCheckService healthCheck;

    @JsonCreator
    public HttpEndpoint(@JsonProperty("name") String name, @JsonProperty("target") String target) {
        this(EndpointType.HTTP, name, target);
    }

    HttpEndpoint(EndpointType type, String name, String target) {
        super(type, name, target);
    }

    public HttpProxy getHttpProxy() {
        return httpProxy;
    }

    public void setHttpProxy(HttpProxy httpProxy) {
        this.httpProxy = httpProxy;
    }

    public HttpClientOptions getHttpClientOptions() {
        return httpClientOptions;
    }

    public void setHttpClientOptions(HttpClientOptions httpClientOptions) {
        this.httpClientOptions = httpClientOptions;
    }

    public HttpClientSslOptions getHttpClientSslOptions() {
        return httpClientSslOptions;
    }

    public void setHttpClientSslOptions(HttpClientSslOptions httpClientSslOptions) {
        this.httpClientSslOptions = httpClientSslOptions;
    }

    @JsonGetter("headers")
    public Map<String, String> getHeaders() {
        return headers;
    }

    @JsonIgnore
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    @JsonSetter("headers")
    private void setHeadersJson(Map<String, String> headers) {
        if (this.headers != null) {
            if (headers != null) {
                headers.forEach(this.headers::putIfAbsent);
            }
        } else {
            this.headers = headers;
        }
    }

    public EndpointHealthCheckService getHealthCheck() {
        return healthCheck;
    }

    public void setHealthCheck(EndpointHealthCheckService healthCheck) {
        this.healthCheck = healthCheck;
    }

    @JsonSetter
    private void setHostHeader(String hostHeader) {
        if (!hostHeader.trim().isEmpty()) {
            Map<String, String> headers = Optional.ofNullable(getHeaders()).orElseGet(LinkedHashMap::new);
            headers.put(HttpHeaders.HOST, hostHeader);
            setHeaders(headers);
        }
    }
    // TODO Inherit handling
}
