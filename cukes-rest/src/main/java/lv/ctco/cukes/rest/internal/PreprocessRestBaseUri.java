/*
 * @(#)PreprocessRestBaseUri.java
 *
 * Copyright Swiss Reinsurance Company, Mythenquai 50/60, CH 8022 Zurich. All rights reserved.
 */

package lv.ctco.cukes.rest.internal;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lv.ctco.cukes.core.internal.context.GlobalWorldFacade;
import lv.ctco.cukes.http.extension.CukesHttpPlugin;
import lv.ctco.cukes.rest.RestCukesOptions;
import lv.ctco.cukes.rest.facade.RestRequestFacade;

@Singleton
public class PreprocessRestBaseUri implements CukesHttpPlugin {

    @Inject
    private RestRequestFacade requestFacade;

    @Inject
    private GlobalWorldFacade worldFacade;

    @Override
    public void beforeRequest(RequestSpecification requestSpecification) {
        if (!requestFacade.isGivenBaseUri() && requestFacade.isActive()) {
            Optional<String> baseUri = worldFacade.get(RestCukesOptions.BASE_URI);
            if (baseUri.isPresent()) {
                requestSpecification.baseUri(baseUri.get());
            }
        }
    }

    @Override
    public void afterRequest(Response response) {
    }

    @Override
    public void beforeAllTests() {
    }

    @Override
    public void afterAllTests() {
    }

    @Override
    public void beforeScenario() {
    }

    @Override
    public void afterScenario() {
    }
}
