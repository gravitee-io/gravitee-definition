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
package io.gravitee.definition.model.services.schedule;

import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.gravitee.definition.model.Service;

/**
 * @author David BRASSELY (david.brassely at graviteesource.com)
 * @author GraviteeSource Team
 */
public abstract class ScheduledService extends Service {

	protected Trigger trigger;

	public ScheduledService(String name) {
		super(name);
	}

	public Trigger getTrigger() {
		return trigger;
	}

	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}

	@JsonSetter // Ensure backward compatibility
	private void setInterval(Long interval) {
		if (trigger == null) {
			trigger = new Trigger();
		}
		trigger.setRate(interval);
	}

	@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
	@JsonSetter // Ensure backward compatibility
	private void setUnit(TimeUnit timeUnit) {
		if (trigger == null) {
			trigger = new Trigger();
		}
		trigger.setUnit(timeUnit);
	}
}
