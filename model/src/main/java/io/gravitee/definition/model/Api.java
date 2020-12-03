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
package io.gravitee.definition.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.gravitee.definition.model.flow.Flow;
import io.gravitee.definition.model.plugins.resources.Resource;
import io.gravitee.definition.model.services.Services;
import io.gravitee.definition.model.services.discovery.EndpointDiscoveryService;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author David BRASSELY (david.brassely at graviteesource.com)
 * @author Azize ELAMRANI (azize.elamrani at graviteesource.com)
 * @author GraviteeSource Team
 */
public class Api implements Serializable {

    private String id;
    private String name;
    private String version = "undefined";
    private DefinitionVersion definitionVersion;
    private Proxy proxy;
    private Services services = new Services();
    private List<Resource> resources = new ArrayList<>();
    private Map<String, List<Rule>> paths;
    private List<Flow> flows;
    private Properties properties;
    private Set<String> tags = new HashSet<>();
    private Map<String, Pattern> pathMappings = new LinkedHashMap<>();
    private Map<String, Map<String, ResponseTemplate>> responseTemplates = new LinkedHashMap<>();
    private Map<String, Plan> plans = new HashMap<>();

    public Api() {
    }

    @JsonCreator
    public Api(@JsonProperty(value = "proxy", required = true) Proxy proxy, @JsonProperty("gravitee") DefinitionVersion definitionVersion)
    {
        this.proxy = proxy;
        this.definitionVersion = definitionVersion == null ? DefinitionVersion.V1 : definitionVersion;
        if (proxy.getGroups() == null && proxy.getCommonEndpointSettings() != null) {
            EndpointGroup group = new EndpointGroup();
            group.setName("default-group");
            group.setEndpoints(proxy.getCommonEndpointSettings().getEndpoints());
            proxy.setGroups(Collections.singleton(group));
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Proxy getProxy() {
        return proxy;
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    public Map<String, List<Rule>> getPaths() {
        return paths;
    }

    public void setPaths(Map<String, List<Rule>> paths) {
        if (definitionVersion != DefinitionVersion.V1) {
            throw new UnsupportedOperationException("Paths are only available for definition 1.x.x");
        }
        this.paths = paths;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @JsonIgnore
    public Properties getProperties() {
        return properties;
    }

    @JsonIgnore
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @JsonSetter("properties")
    @JsonDeserialize(using = PropertiesDeserializer.class)
    public void setPropertyList(List<Property> properties) {
        this.properties = new Properties();
        this.properties.setProperties(properties);
    }

    @JsonGetter("properties")
    public List<Property> getPropertyList() {
        if (properties != null) {
            return properties.getProperties();
        }
        return Collections.emptyList();
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
        if (services != null) {
            // Add compatibility with Definition 1.22
            EndpointDiscoveryService discoveryService = services.get(EndpointDiscoveryService.class);
            if (discoveryService != null) {
                services.remove(EndpointDiscoveryService.class);
                Set<EndpointGroup> endpointGroups = proxy.getGroups();
                if (endpointGroups != null && !endpointGroups.isEmpty()) {
                    EndpointGroup defaultGroup = endpointGroups.iterator().next();
                    defaultGroup.getServices().put(EndpointDiscoveryService.class, discoveryService);
                }
            }
        }
    }

    public <T extends Service> T getService(Class<T> serviceClass) {
        return this.services.get(serviceClass);
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        Set<String> distinct = new HashSet<>(resources.size());
        resources.removeIf(p -> !distinct.add(p.getName()));
        this.resources = resources;
    }

    @JsonIgnore
    public Map<String, Pattern> getPathMappings() {
        return pathMappings;
    }

    @JsonIgnore
    public void setPathMappings(Map<String, Pattern> pathMappings) {
        this.pathMappings = pathMappings;
    }

    @JsonGetter("path_mappings")
    public Set<String> getPathMappingsJson() {
        return pathMappings.keySet();
    }

    @JsonSetter("path_mappings")
    public void setPathMappingsJson(Set<String> pathMappings) {
        if (pathMappings == null) {
            this.pathMappings = null;
            return;
        }
        this.pathMappings = new LinkedHashMap<>();
        pathMappings.forEach(pathMapping -> this.pathMappings.put(
                pathMapping,
                Pattern.compile(pathMapping.replaceAll(":\\w*", "[^\\/]*") + "/*")
        ));
    }

    @JsonProperty("response_templates")
    public Map<String, Map<String, ResponseTemplate>> getResponseTemplates() {
        return responseTemplates;
    }

    public void setResponseTemplates(Map<String, Map<String, ResponseTemplate>> responseTemplates) {
        this.responseTemplates = responseTemplates;
    }

    public List<Flow> getFlows() {
        return flows;
    }

    public void setFlows(List<Flow> flows) {
        if (definitionVersion == DefinitionVersion.V1) {
            throw new UnsupportedOperationException("Flows are only available for definition >= 2.x.x");
        }
        this.flows = flows;
    }

    @JsonGetter("gravitee")
    public DefinitionVersion getDefinitionVersion() {
        return definitionVersion;
    }

    public Plan getPlan(String plan) {
        return plans.get(plan);
    }

    public List<Plan> getPlans() {
        return new ArrayList<>(plans.values());
    }

    public void setPlans(List<Plan> plans) {
        this.plans.clear();
        for (Plan plan : plans) {
            this.plans.put(plan.getId(), plan);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Api api = (Api) o;
        return Objects.equals(id, api.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String toString() {
        return "Api{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
