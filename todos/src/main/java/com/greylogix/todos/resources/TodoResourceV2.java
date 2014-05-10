package com.greylogix.todos.resources;

// import java.io.FileInputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.*;
import java.util.regex.*;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
// import javax.ws.rs.core.Response.Status;

import org.bson.types.*;

// import org.apache.commons.io.IOUtils;

// import com.google.common.base.Optional;
import com.greylogix.todos.model.Todo2;
import com.mongodb.*;

@Path("/todos")
@Produces("application/json")
public class TodoResourceV2 {
	
	public TodoResourceV2() {
		
	}

    @GET
    public List<Todo2> getMainView() throws IOException {
    	
    	
    	DBCollection coll = connect();
    	   	
    	Cursor c = coll.find();
    	
    	LinkedList<Todo2> todos = new LinkedList<Todo2>();
    	
    	try {
	    	while(c.hasNext()) {
	    		todos.add(toTodo(c.next()));
	    		
	    	}
    	} finally {
    		c.close();
    	}
    	
    	return todos;
    }
    
    static Todo2 toTodo(DBObject d) {
    	
    	return new Todo2(((ObjectId)d.get("_id")).toString(), (String)d.get("task"), (Date)d.get("start"), (Date)d.get("due"));
    	
    }
    
    static DBObject toDBObject(Todo2 todo) {
    	return null; // new DBObject();
    }
    
    static DBCollection connect() {
    	MongoClient client = null;
    	
    	try {
    		client = new MongoClient("localhost");
    	} catch(UnknownHostException e) {
    		throw new RuntimeException();
    	}
    	
    	DB db = client.getDB("todosDB");
    	
    	DBCollection coll = db.getCollection("todos");
    	
    	return coll;
    }
    
    @GET
    @Path("{id}")
    public Todo2 getOneTodo(@PathParam("id") String id) throws IOException {
    	
    	DBCollection coll = connect();
    	
    	BasicDBObject f = new BasicDBObject("_id", new ObjectId(id));
    	
    	return toTodo(coll.findOne(f));
    }
    
    @GET
    @Path("/search/{id}")
    public List<Todo2> searchTodo(@PathParam("id") String name) {
    	
    	DBCollection coll = connect();
    	
    	BasicDBObject f = new BasicDBObject("task", Pattern.compile(name));
    	
    	List<DBObject> todos = coll.find(f).toArray();
    	
    	List<Todo2> todos2 = new LinkedList<Todo2>();
    	
    	for(DBObject todo : todos) {
    		todos2.add(toTodo(todo));
    	}
    	
    	return todos2;
    }
    
    @GET
    @Path("/search")
    public List<Todo2> searchAllTodos() throws IOException {
    	return getMainView();
    }
    
    @POST
    public Response newTodo(Todo2 todo) {
    	
    	DBCollection coll = connect();
    	
    	BasicDBObject f = new BasicDBObject("task", todo.getTask())
    			.append("start", todo.getStart())
    			.append("due", todo.getDue());
    	
    	coll.insert(f);
    		
    	return Response.ok().build();
    }
    
    @PUT
    public Response editTodo(Todo2 todo) {
    	
    	DBCollection coll = connect();
    	
    	BasicDBObject f = new BasicDBObject("_id", new ObjectId(todo.getId()));
    	BasicDBObject u = new BasicDBObject("task", todo.getTask())
    		.append("start", todo.getStart())
    		.append("due", todo.getDue());
    	
    	
    	coll.update(f, u);
    	    	
    	return Response.ok().build();
    }
    
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String id) throws IOException {
    	
    	DBCollection coll = connect();
    	
    	BasicDBObject f = new BasicDBObject("_id", new ObjectId(id));
    	
    	coll.remove(f);
    	
    	return Response.ok().build();
    }
    
}
