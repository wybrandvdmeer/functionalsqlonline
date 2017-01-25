package functionalsqlonline.controller;

public class StatementContainer {

    private String statement;
    private String error;

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStatement() {
        return statement;
    }
}
