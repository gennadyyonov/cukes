package lv.ctco.cukes.rest.facade;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.restassured.specification.RequestSpecification;
import lv.ctco.cukes.core.internal.context.InflateContext;
import lv.ctco.cukes.http.facade.HttpRequestFacade;

@Singleton
@InflateContext
public class RestRequestFacade {

    @Inject
    HttpRequestFacade requestFacade;

    private String requestBody;
    private boolean active;

    public void initNewSpecification() {
        this.requestBody = null;
        setActive(false);
    }

    private RequestSpecification specification() {
        return requestFacade.value();
    }

    public void formParam(String parameterName, String parameterValue) {
        specification().formParam(parameterName, parameterValue);
    }

    public void multiPart(String contentBody, String controlName) {
        specification().multiPart(controlName, contentBody);
    }

    public void multiPart(byte[] contentBody, String controlName) {
        specification().multiPart(controlName, "file.txt", contentBody);
    }

    public void multiPart(String contentBody, String controlName, String mimeType) {
        specification().multiPart(controlName, contentBody, mimeType);
    }

    public void multiPart(byte[] contentBody, String controlName, String mimeType) {
        specification().multiPart(controlName, "file.txt", contentBody, mimeType);
    }

    public void body(String body) {
        specification().body(body);
    }

    public void body(byte[] body) {
        specification().body(body);
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public void clearRequestBody() {
        this.requestBody = null;
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
