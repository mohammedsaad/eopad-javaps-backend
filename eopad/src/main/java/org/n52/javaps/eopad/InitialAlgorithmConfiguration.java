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

import org.n52.javaps.transactional.TransactionalAlgorithmConfiguration;
import org.n52.javaps.transactional.TransactionalAlgorithmConfigurer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitialAlgorithmConfiguration implements TransactionalAlgorithmConfigurer {

    private static final String STRING_REPLACE
            = "https://raw.githubusercontent.com/matthesrieke/string-replace/master/src/main/resources/application-package.json";

    @Override
    public void configure(TransactionalAlgorithmConfiguration configuration) {
        configuration.addAlgorithmFromResource(STRING_REPLACE);
    }
}
