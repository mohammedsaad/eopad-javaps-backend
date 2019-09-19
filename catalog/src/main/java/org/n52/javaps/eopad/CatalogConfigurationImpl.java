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
package org.n52.javaps.eopad;

import okhttp3.HttpUrl;
import org.n52.faroe.Validation;
import org.n52.faroe.annotation.Configurable;
import org.n52.faroe.annotation.Setting;
import org.n52.iceland.service.ServiceSettings;
import org.n52.janmayen.function.Predicates;
import org.n52.janmayen.stream.Streams;
import org.n52.javaps.transactional.TransactionalAlgorithmRepository;
import org.n52.shetland.ogc.wps.ap.ApplicationPackage;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

@Configurable
public class CatalogConfigurationImpl implements CatalogConfiguration {
    private HttpUrl serviceURL;

    private final Collection<TransactionalAlgorithmRepository> repositories;
    private final Catalog catalog;

    public CatalogConfigurationImpl(Catalog catalog, Collection<TransactionalAlgorithmRepository> repositories) {
        this.catalog = Objects.requireNonNull(catalog);
        this.repositories = Objects.requireNonNull(repositories);
    }

    @Override
    public Catalog getCatalog() {
        return catalog;
    }

    @Override
    public HttpUrl getServiceURL() {
        return serviceURL;
    }

    @Override
    public HttpUrl getProcessUrl(String id) {
        HttpUrl resolve = getServiceURL().resolve("processes/");
        if (resolve == null) {
            throw new IllegalArgumentException();
        }
        return resolve.resolve(id);
    }

    @Setting(ServiceSettings.SERVICE_URL)
    public void setServiceURL(URI serviceURL) {
        Validation.notNull("serviceURL", serviceURL);
        HttpUrl httpUrl = HttpUrl.get(serviceURL);
        if (httpUrl == null) {
            throw new IllegalArgumentException();
        }
        httpUrl = httpUrl.resolve("./rest");
        if (httpUrl == null) {
            throw new IllegalArgumentException();
        }
        HttpUrl.Builder builder = httpUrl.newBuilder();
        this.serviceURL = builder.query(null).build();
    }

    @Override
    public Stream<ApplicationPackage> getApplicationPackages() {
        return repositories.stream().flatMap(this::getApplicationPackages);
    }

    private Stream<ApplicationPackage> getApplicationPackages(TransactionalAlgorithmRepository repository) {
        return repository.getAlgorithmNames().stream().map(repository::getApplicationPackage)
                         .filter(Optional::isPresent).map(Optional::get);
    }

    @Override
    public String getServiceIdentifier() {
        return asIdentifier(getServiceURL());
    }

    private String asIdentifier(HttpUrl url) {
        List<String> domain = Streams.stream(url.host().split("\\."))
                                     .collect(Collectors.toList());
        Collections.reverse(domain);
        Stream<String> path = Arrays.stream(url.encodedPath().split("/"))
                                    .filter(Predicates.not(String::isEmpty));
        return Stream.concat(domain.stream(), path).collect(joining("."));
    }
}
