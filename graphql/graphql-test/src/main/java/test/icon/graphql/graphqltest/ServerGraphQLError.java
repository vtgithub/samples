package test.icon.graphql.graphqltest;

import feign.FeignException;
import graphql.ErrorType;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;
import java.util.Map;

public class ServerGraphQLError implements GraphQLError {

    private GraphQLError error;
    private String message;

    public ServerGraphQLError(GraphQLError error) {
        this.error = error;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return error.getExtensions();
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }

    @Override
    public List<Object> getPath() {
        return error.getPath();
    }

    @Override
    public Map<String, Object> toSpecification() {
        return error.toSpecification();
    }

    @Override
    public String getMessage() {
        if (error instanceof ExceptionWhileDataFetching){
//            System.out.println(((ExceptionWhileDataFetching) error).getException().getCause().);
            if (((ExceptionWhileDataFetching) error).getException() instanceof FeignException)
                return "related to microservices call";
            else
                return "internal server error";
        }else{
            return error.getMessage();
        }
//        return (error instanceof ExceptionWhileDataFetching) ? ((ExceptionWhileDataFetching) error).getException().getMessage() : error.getMessage();
    }

}