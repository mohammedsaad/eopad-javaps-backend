/*
 * Copyright 2019 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.n52.javaps.docker.process;

import com.github.dockerjava.api.DockerClient;
import org.n52.javaps.description.TypedProcessDescription;
import org.n52.javaps.docker.DelegatingDockerConfig;
import org.n52.javaps.docker.Environment;
import org.n52.javaps.engine.ProcessExecutionContext;
import org.slf4j.Logger;

import java.util.Objects;

public class DelegatingDockerJobConfig extends DelegatingDockerConfig implements DockerJobConfig {
    private DockerJobConfig delegate;

    public DelegatingDockerJobConfig(DockerJobConfig delegate) {
        super(delegate);
        this.delegate = Objects.requireNonNull(delegate);
    }

    @Override
    protected DockerJobConfig getDelegate() {
        return delegate;
    }

    @Override
    public Logger getLog() {
        return delegate.getLog();
    }

    @Override
    public DockerClient getClient() {
        return delegate.getClient();
    }

    @Override
    public TypedProcessDescription getDescription() {
        return delegate.getDescription();
    }

    @Override
    public ProcessExecutionContext getContext() {
        return delegate.getContext();
    }

    @Override
    public Environment getJobEnvironment() {
        return delegate.getJobEnvironment();
    }

    @Override
    public String getHelperContainerId() {
        return delegate.getHelperContainerId();
    }

    @Override
    public void setHelperContainerId(String helperContainerId) {
        delegate.setHelperContainerId(helperContainerId);
    }

    @Override
    public String getProcessContainerId() {
        return delegate.getProcessContainerId();
    }

    @Override
    public void setProcessContainerId(String processContainerId) {
        delegate.setProcessContainerId(processContainerId);
    }

    @Override
    public String getVolumeId() {
        return delegate.getVolumeId();
    }

    @Override
    public void setVolumeId(String volumeId) {
        delegate.setVolumeId(volumeId);
    }
}
