package io.swagger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.Reader;
import io.swagger.matchers.SerializationMatchers;
import io.swagger.models.Swagger;
import io.swagger.models.parameters.QueryParameter;

import org.testng.annotations.Test;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

public class DefaultValueTest {

    @Test
    public void scanResourceWithDefaultValue() {
        final Swagger swagger = new Reader(new Swagger()).read(ResourceWithDefaultValue.class);
        QueryParameter qp = (QueryParameter) swagger.getPath("/test").getGet().getParameters().get(0);
        final String json = "{" +
                "   \"name\":\"page\"," +
                "   \"in\":\"query\"," +
                "   \"description\":\"Page to be returned\"," +
                "   \"required\":false," +
                "   \"type\":\"integer\"," +
                "   \"default\":1," +
                "   \"format\":\"int32\"" +
                "}";
        SerializationMatchers.assertEqualsToJson(qp, json);
    }

    @Api("/test")
    @Path("/test")
    public class ResourceWithDefaultValue {
        @ApiOperation(value = "test")
        @GET
        public void getTest(@QueryParam("page") @DefaultValue("1") @ApiParam(value = "Page to be returned") Integer page) {
            return;
        }
    }
}
