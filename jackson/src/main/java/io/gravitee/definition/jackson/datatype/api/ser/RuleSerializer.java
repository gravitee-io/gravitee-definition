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

import io.gravitee.common.http.HttpMethod;
import io.gravitee.definition.model.Rule;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

/**
 * @author David BRASSELY (brasseld at gmail.com)
 * @author Gravitee.io Team
 */
public class RuleSerializer extends StdScalarSerializer<Rule> {

    public RuleSerializer(Class<Rule> t) {
        super(t);
    }

    @Override
    public void serialize(Rule rule, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        if (rule.getPolicy() != null) {
            jgen.writeStartObject();

            jgen.writeFieldName("methods");

            jgen.writeStartArray();
            for (HttpMethod method : rule.getMethods()) {
                jgen.writeString(method.toString().toUpperCase());
            }
            jgen.writeEndArray();

            jgen.writeObject(rule.getPolicy());

            if (rule.getDescription() != null) {
                jgen.writeStringField("description", rule.getDescription());
            }

            jgen.writeEndObject();
        }
    }
}
