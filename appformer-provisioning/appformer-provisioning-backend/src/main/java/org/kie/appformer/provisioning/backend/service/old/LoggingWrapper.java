/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.appformer.provisioning.backend.service.old;

import org.slf4j.Logger;

public class LoggingWrapper implements OutputHandler {

    private final OutputHandler handler;
    private Logger logger;

    public LoggingWrapper( final OutputHandler handler, Logger logger ) {
        this.handler = handler;
        this.logger = logger;
    }

    @Override
    public void handleOutput( String line ) {
        logger.info( line );
        handler.handleOutput( line );
    }

}
