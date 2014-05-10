package com.greylogix.todos.resources;

// simport java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
// import javax.ws.rs.core.Response.Status;

// import org.apache.commons.io.IOUtils;

// import com.google.common.base.Optional;
import com.greylogix.todos.model.Todo;

@Path("/todos")
@Produces("application/json")
public class TodoResource {
	
	public TodoResource() {
		
		Todo [] initTodos = new Todo [] { 
				new Todo("1", new Date(), new Date()), 
				new Todo("2", new Date(), new Date()),
				new Todo("3", new Date(), new Date()),
				new Todo("4", new Date(), new Date())};
		
		for(Todo todo : initTodos) {
			todos.put(todo.getId(), todo);
		}
	}

    /**
     * Checks if the index.html exists if the result is null a IOException will be thrown. In the next step it will be decided if the
     * Response is ok if a index.html exists or a serverError if a index.html do not exists. All user roles have access to call this method.
     *
     * @return Response
     * @throws IOException
     */
    @GET
    public List<Todo> getMainView() throws IOException {
    	
    	return new LinkedList<Todo>(todos.values());
    }
    
    @GET
    @Path("{id}")
    public Todo getOneTodo(@PathParam("id") String id) throws IOException {
    	
    	return todos.get(id);
    }
    
    @POST
    public Response newTodo(Todo todo) {
    	
    	// todos.add(todo);
    	
    	todos.put(todo.getId(), todo);    	
    	return Response.ok().build();
    }
    
    @PUT
    public Response editTodo(Todo todo) {
    	
    	editTodo(todo.getId(), todo.getTask(), todo.getStart(), todo.getDue());
    	
    	return Response.ok().build();
    }
    
    public void editTodo(String id, String task, Date start, Date due) {
    	
    	Todo todo = todos.get(id);
    	
    	if(todo != null) {
    		todo.setTask(task);
    		todo.setStart(start);
    		todo.setDue(due);   		
    	}
    	
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String id) throws IOException {
    	
    	/*
    	for(int i = 0; i < todos.size(); i++) {
    		if(name.equals(todos.get(i).getTask())) {
    			todos.remove(i);
    			return Response.ok().build();
    		}
    	}
    	
    	  	
    	return Response.status(Status.BAD_REQUEST).build();
    	*/
    	todos.remove(id);
    	return Response.ok().build();
    }
    
    /*
    public void deleteTodo(String name) {
    	
    	for(int i = 0; i < todos.size(); i++) {
    		if(name.equals(todos.get(i).getTask())) {
    			todos.remove(i);
    			break;
    		}
    	}
    }
    */
    
    HashMap<String, Todo> todos = new HashMap<String, Todo>();
}
