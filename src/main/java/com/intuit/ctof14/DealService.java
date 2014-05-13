package com.intuit.ctof14;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import com.intuit.ctof14.ReviewService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("v1/deal")
@Api(value="v1/deal", description="Deal API - this service stores and returns deals")
public class DealService
{
  @GET
  @Consumes("application/json")
  @ApiOperation(value="returns all deals when no dealId is provided , else corressponding deal for id provided")
  public Response getDeal(@QueryParam("dealId") String dealId) {
      try {
      InputStream stream = this.getClass().getResourceAsStream("/deals.json");
      StringWriter writer = new StringWriter();
      IOUtils.copy(stream, writer, "UTF-8");
      
      JSONObject deals = new JSONObject(writer.toString());
      
      String dealData = dealId != null ? deals.get(dealId).toString() : deals.toString();
      
      return Response.ok(dealData, MediaType.APPLICATION_JSON_TYPE).build();
    } catch (IOException ex) {
      return Response.status(500).build();      
    }

  }

}