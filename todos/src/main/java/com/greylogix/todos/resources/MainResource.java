package com.greylogix.todos.resources;

import java.io.FileInputStream;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;

import com.google.common.base.Optional;

@Path("/")
@Produces("text/html;charset=UTF-8")
public class MainResource {

	private static final String PATHTOINDEXPAGE = "client/dist/index.html";//"src/main/resources/assets/index.html";

    /**
     * Checks if the index.html exists if the result is null a IOException will be thrown. In the next step it will be decided if the
     * Response is ok if a index.html exists or a serverError if a index.html do not exists. All user roles have access to call this method.
     *
     * @return Response
     * @throws IOException
     */
    @GET
    public Response getMainView() throws IOException {
    	
        Optional<String> html = Optional.absent();

        try (FileInputStream inputStream = new FileInputStream(PATHTOINDEXPAGE)) {
            html = Optional.fromNullable(IOUtils.toString(inputStream));
        }
        if (html.isPresent()) {
            return Response.ok().entity(html).build();
        } else {
            return Response.serverError().build();
        }
        
    	// return Response.ok().entity(Optional.fromNullable("<html><body>Hello, world! </body></html>")).build();
    }
    
    
}
