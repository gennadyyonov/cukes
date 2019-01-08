package lv.ctco.cukes.graphql.internal;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lv.ctco.cukes.core.internal.templating.TemplatingEngine;
import lv.ctco.cukes.graphql.facade.GQLRequestFacade;
import lv.ctco.cukes.http.extension.CukesHttpPlugin;

import static com.google.common.base.Strings.isNullOrEmpty;

@Singleton
public class PreprocessGraphQLRequestBody implements CukesHttpPlugin {

    @Inject
    GQLRequestFacade requestFacade;

    @Inject
    TemplatingEngine templatingEngine;

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

    @Override
    public void beforeRequest(RequestSpecification requestSpecification) {
        GraphQLRequest graphQLRequest = requestFacade.getGraphQLRequest();
        String query = graphQLRequest.getQuery();
        String variables = graphQLRequest.getVariables();
        if (!isNullOrEmpty(query) || !isNullOrEmpty(variables)) {
            if (!isNullOrEmpty(query)) {
                graphQLRequest.setQuery(templatingEngine.processBody(query));
            }
            if (!isNullOrEmpty(variables)) {
                graphQLRequest.setVariables(templatingEngine.processBody(variables));
            }
            requestSpecification.body(graphQLRequest);
        }
    }

    @Override
    public void afterRequest(Response response) {

    }
}
