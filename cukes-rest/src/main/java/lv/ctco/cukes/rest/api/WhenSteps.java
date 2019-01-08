package lv.ctco.cukes.rest.api;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import cucumber.api.java.en.When;
import lv.ctco.cukes.core.internal.context.InflateContext;
import lv.ctco.cukes.http.facade.HttpResponseFacade;
import lv.ctco.cukes.rest.facade.RestRequestFacade;

@Singleton
@InflateContext
public class WhenSteps {

    @Inject
    private HttpResponseFacade facade;
    @Inject
    private RestRequestFacade restRequestFacade;

    @When("^the client performs (.+) request on \"(.+)\"$")
    public void perform_Http_Request(String httpMethod, String url) throws Throwable {
        try {
            restRequestFacade.setActive(true);
            facade.setResponsePrefix("");
            facade.doRequest(httpMethod, url);
        } finally {
            restRequestFacade.setActive(false);
        }
    }
}
