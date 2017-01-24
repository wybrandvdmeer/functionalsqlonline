package functionalsqlonline.controller;


import functionalsql.FunctionalSQLCompiler;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("functionalsql")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class Controller {
    @GET
    @Path("/{functionalsql:.+}")
    public String translate(@PathParam("functionalsql") String functionalSql) throws Exception {
        FunctionalSQLCompiler compiler = new FunctionalSQLCompiler();
        return compiler.parse(functionalSql);
    }
}
