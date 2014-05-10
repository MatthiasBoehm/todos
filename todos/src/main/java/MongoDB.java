
import com.mongodb.*;

import java.util.*;

public class MongoDB {

	public static void main(String[] args) throws Exception {
		
		MongoClient mongoClient = new MongoClient( "localhost" );

		DB db = mongoClient.getDB( "myTest" );


		Set<String> colls = db.getCollectionNames();

		for (String s : colls) {
		    System.out.println(s);
		}

		DBCollection coll = db.getCollection("testData");
		
		
		for(int i = 0; i < 20; i++) {
			BasicDBObject doc = new BasicDBObject("y", i).append("z", i+1);
			
			coll.insert(doc);
		}
		
		System.out.println(coll.count());
		
		BasicDBObject query = new BasicDBObject("z", 10); 
		
		DBObject myDoc = coll.findOne(query);
		System.out.println(myDoc);
		
		BasicDBObject query2 = new BasicDBObject("x", 3);
		
		DBCursor cursor = coll.find(query2);
		
		try {
			while(cursor.hasNext()) {
				DBObject d = cursor.next();
				System.out.println(d);
			}
		} finally {
			cursor.close();
		}

		
	}

}
