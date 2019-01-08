/*
 * @(#)PreprocessGraphQLBaseUri.java
 *
 * Copyright Swiss Reinsurance Company, Mythenquai 50/60, CH 8022 Zurich. All rights reserved.
 */

package lv.ctco.cukes.graphql.internal;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lv.ctco.cukes.core.internal.context.GlobalWorldFacade;
import lv.ctco.cukes.graphql.GraphQLCukesOptions;
import lv.ctco.cukes.graphql.facade.GQLRequestFacade;
import lv.ctco.cukes.http.extension.CukesHttpPlugin;

@Singleton
public class PreprocessGraphQLBaseUri implements CukesHttpPlugin {

    @Inject
    private GQLRequestFacade requestFacade;

    @Inject
    private GlobalWorldFacade worldFacade;

    @Override
    public void beforeRequest(RequestSpecification requestSpecification) {
        if (!requestFacade.isGivenBaseUri() && requestFacade.isActive()) {
            Optional<String> baseUri = worldFacade.get(GraphQLCukesOptions.BASE_URI);
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
