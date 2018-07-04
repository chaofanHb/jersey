package com.spring_jersey.api;

/**
 * Created by Administrator on 2017/12/18.
 */

import com.spring_jersey.service.TestService;
import com.sun.jersey.spi.resource.Singleton;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/tokenapi")
@Component
@Scope("request")
@Singleton
@SuppressWarnings("unqualified-field-access")
public class WebService {
    protected Log logger = LogFactory.getLog(getClass());

    @Autowired
    private TestService testService;

    @SuppressWarnings("nls")
    @GET
    @Scope("request")
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld() { // @PathParam("username") String username
        String ret = "Hello World!";
        return ret;
    }

    @SuppressWarnings({"nls", "unqualified-field-access"})
    @GET
    @Scope("request")
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/crtchallengecode/{tokenid}")
    public String crtChallengeCode(@PathParam("tokenid")
                                   String tokenid, @Context
                                   HttpServletRequest request) {
        System.out.println("token id in--------------->" + tokenid + "/"
                + request.getRemoteAddr());
        return testService.get();
    }

    @SuppressWarnings({"nls", "unqualified-field-access"})
    @GET
    @Scope("request")
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/verify/{tokenid}/{challengecode}")
    public String verify(@PathParam("tokenid")
                         String tokenid, @PathParam("challengecode")
                         String challengecode, @Context
                         HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        return clientIp;
    }

}
