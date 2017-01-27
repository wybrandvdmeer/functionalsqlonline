package functionalsqlonline.controller;


import functionalsql.FunctionalSQLCompiler;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("parse")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class Controller {
    @GET
    @Path("/{functionalsql:.+}")
    public StatementContainer translate(@PathParam("functionalsql") String functionalSql) throws Exception {
        StatementContainer statementContainer = new StatementContainer();
        try {
            FunctionalSQLCompiler compiler = new FunctionalSQLCompiler();
            statementContainer.setStatement(compiler.parse(functionalSql));
            return statementContainer;
        } catch(Exception e) {
            if(e.getMessage() != null) {
                statementContainer.setError(e.getMessage());
                return statementContainer;
            }
            throw e;
        }
    }
}
