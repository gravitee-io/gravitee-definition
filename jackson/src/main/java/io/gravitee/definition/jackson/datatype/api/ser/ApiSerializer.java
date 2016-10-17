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
package io.gravitee.definition.jackson.datatype.api.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import io.gravitee.definition.model.Api;
import io.gravitee.definition.model.plugins.resources.Resource;

import java.io.IOException;

/**
 * @author David BRASSELY (david.brassely at graviteesource.com)
 * @author GraviteeSource Team
 */
public class ApiSerializer extends StdScalarSerializer<Api> {

    public ApiSerializer(Class<Api> t) {
        super(t);
    }

    @Override
    public void serialize(Api api, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeStringField("id", api.getId());
        jgen.writeStringField("name", api.getName());
        jgen.writeObjectField("version", api.getVersion());

        if (api.getProxy() != null) {
            jgen.writeObjectField("proxy", api.getProxy());
        }

        if (api.getPaths() != null) {
            jgen.writeObjectFieldStart("paths");
            api.getPaths().forEach((s, path) -> {
                try {
                    jgen.writeObjectField(s, path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            jgen.writeEndObject();
        }

        if (api.getServices() != null && ! api.getServices().isEmpty()) {
            jgen.writeObjectField("services", api.getServices());
        }

        if (api.getResources() != null && ! api.getResources().isEmpty()) {
            jgen.writeArrayFieldStart("resources");
            for(Resource resource : api.getResources()) {
                jgen.writeObject(resource);
            }
            jgen.writeEndArray();
        }

        if (api.getProperties() != null) {
            jgen.writeObjectFieldStart("properties");
            api.getProperties().forEach((s, property) -> {
                try {
                    jgen.writeObjectField(s, property);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            jgen.writeEndObject();
        }

        if (api.getTags() != null && !api.getTags().isEmpty()) {
            jgen.writeArrayFieldStart("tags");
            api.getTags().forEach(tag -> {
                try {
                    jgen.writeObject(tag);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            jgen.writeEndArray();
        }

        if (api.getViews() != null && !api.getViews().isEmpty()) {
            jgen.writeArrayFieldStart("views");
            api.getViews().forEach(view -> {
                try {
                    jgen.writeObject(view);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            jgen.writeEndArray();
        }

        jgen.writeEndObject();
    }
}
