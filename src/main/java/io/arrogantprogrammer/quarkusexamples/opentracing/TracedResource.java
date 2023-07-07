package io.arrogantprogrammer.quarkusexamples.opentracing;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Path("/hello")
public class TracedResource {

    static final Logger LOGGER = LoggerFactory.getLogger(TracedResource.class);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        LOGGER.info("hello");
        return "Hello!";
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String helloPersonalized(@PathParam("name") String name) {
        LOGGER.info("helloPersonalized: {}", name);
        Name nameToSave = new Name();
        nameToSave.value = name;
        nameToSave.persist();
        LOGGER.debug("saved: {}", nameToSave);
        return "Hello, " + name + "!";
    }

    @GET
    @Path("/all")
    @Produces(MediaType.TEXT_PLAIN)
    public List<String> allNames() {
        LOGGER.info("allNames");
        return Name.listAll().stream().map(name -> {
            return name.toString();
        }).collect(Collectors.toList());
    }

}
