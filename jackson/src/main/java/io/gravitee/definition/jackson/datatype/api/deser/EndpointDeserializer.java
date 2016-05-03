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
package io.gravitee.definition.jackson.datatype.api.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import io.gravitee.definition.model.Endpoint;

import java.io.IOException;

/**
 * @author David BRASSELY (brasseld at gmail.com)
 * @author GraviteeSource Team
 */
public class EndpointDeserializer extends StdScalarDeserializer<Endpoint> {

    public EndpointDeserializer(Class<Endpoint> vc) {
        super(vc);
    }

    @Override
    public Endpoint deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        Endpoint endpoint = new Endpoint();
        endpoint.setTarget(node.get("target").asText());

        JsonNode weightNode = node.get("weight");
        if (weightNode != null) {
            int weight = weightNode.asInt(Endpoint.DEFAULT_WEIGHT);
            endpoint.setWeight(weight);
        } else {
            endpoint.setWeight(Endpoint.DEFAULT_WEIGHT);
        }

        JsonNode backupNode = node.get("backup");
        if (backupNode != null) {
            boolean backup = backupNode.asBoolean(false);
            endpoint.setBackup(backup);
        } else {
            endpoint.setBackup(false);
        }

        return endpoint;
    }
}