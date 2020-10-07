package io.rtr.cuny;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.constraints.NotEmpty;

public class MembershipConfiguration extends Configuration {
    private String message;

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @JsonProperty
    public void setDatabase(DataSourceFactory database) {
        this.database = database;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
