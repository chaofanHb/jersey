package com.jerseyApi.json;

/**
 * Created by Administrator on 2017/12/18.
 */

import com.jerseyApi.dto.Request;
import com.jerseyApi.dto.Response;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.glassfish.grizzly.http.server.HttpServer;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

@Path("query")
public class JsonResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response query(Request req) {
        System.out.println(req.getQuery());

        Response resp = new Response();
        resp.setRespCode(0);
        resp.setRespDesc(req.getQuery());
        return resp;
    }

    public static void main(String[] args) {
        URI uri = UriBuilder.fromUri("http://127.0.0.1").port(10000).build();
        ResourceConfig rc = new PackagesResourceConfig("com.jerseyApi.json");
        //使用Jersey对POJO的支持，必须设置为true
        rc.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, true);
        try {
            HttpServer server = GrizzlyServerFactory.createHttpServer(uri, rc);
            server.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
