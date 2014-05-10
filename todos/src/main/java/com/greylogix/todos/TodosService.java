package com.greylogix.todos;

import org.eclipse.jetty.server.session.SessionHandler;

import com.bazaarvoice.dropwizard.assets.ConfiguredAssetsBundle;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.greylogix.todos.resources.MainResource;
import com.greylogix.todos.resources.TodoResourceV2;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

/**
 * This is the main entry point for the application and defines the dropwizard service
 *
 * @author chapke
 * @formatter:off
 */
public class TodosService extends Service<TodosConfiguration> {

    
    private static final String ASSETS = "assets";
//    private static final String WEBJARS = "webjars";
//    private static final String PATHTOWEBJARS = "/META-INF/resources/webjars";

    

    /**
     * Main method starts the CalculationService
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new TodosService().run(args);
    }

    /**
     * Preparations for the application start up
     */
    @Override
    public void initialize(Bootstrap<TodosConfiguration> bootstrap) {
        
        bootstrap.addBundle(new ConfiguredAssetsBundle("/" + ASSETS + "/", "/" + ASSETS + "/"));
//        bootstrap.addBundle(new AssetsBundle(PATHTOWEBJARS, "/" + WEBJARS));


        bootstrap.getObjectMapperFactory().enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
        bootstrap.getObjectMapperFactory().enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
    }

    /**
     * Start up the application (dropwizard service) by set a SessionHandler and adding all Resources which this service should provide
     */
    @Override
    public void run(TodosConfiguration calculationConfiguration, Environment environment) {
        
        environment.setSessionHandler(new SessionHandler());

        environment.addResource(new MainResource());
        environment.addResource(new TodoResourceV2());
        
    }
}