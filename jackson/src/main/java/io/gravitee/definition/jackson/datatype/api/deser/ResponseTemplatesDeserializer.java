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
import io.gravitee.definition.model.ResponseTemplate;
import io.gravitee.definition.model.ResponseTemplates;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * @author David BRASSELY (david.brassely at graviteesource.com)
 * @author GraviteeSource Team
 */
public class ResponseTemplatesDeserializer extends StdScalarDeserializer<ResponseTemplates> {

    public ResponseTemplatesDeserializer(Class<ResponseTemplates> vc) {
        super(vc);
    }

    @Override
    public ResponseTemplates deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        ResponseTemplates templates = new ResponseTemplates();

        node.fields().forEachRemaining(jsonNode -> {
            try {
                ResponseTemplate responseTemplate = jsonNode.getValue().traverse(jp.getCodec()).readValueAs(ResponseTemplate.class);
                if (templates.getTemplates() == null) {
                    templates.setTemplates(new LinkedHashMap<>());
                }

                templates.getTemplates().put(jsonNode.getKey(), responseTemplate);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return templates;
    }
}
