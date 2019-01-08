package lv.ctco.cukes.graphql.facade;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.restassured.specification.RequestSpecification;
import lv.ctco.cukes.core.internal.context.InflateContext;
import lv.ctco.cukes.graphql.internal.GraphQLRequest;
import lv.ctco.cukes.http.facade.HttpRequestFacade;

@Singleton
@InflateContext
public class GQLRequestFacade {

    private GraphQLRequest graphQLRequest = new GraphQLRequest();
    private boolean active;

    @Inject
    private HttpRequestFacade requestFacade;

    public void initNewSpecification() {
        graphQLRequest = new GraphQLRequest();
        setActive(false);
    }

    private RequestSpecification specification() {
        return requestFacade.value();
    }

    public void queryBody(String body) {
        graphQLRequest.setQuery(body);
    }

    public void body(GraphQLRequest request) {
        specification().body(request);
    }

    public GraphQLRequest getGraphQLRequest() {
        return graphQLRequest;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isGivenBaseUri() {
        return requestFacade.isGivenBaseUri();
    }
}
