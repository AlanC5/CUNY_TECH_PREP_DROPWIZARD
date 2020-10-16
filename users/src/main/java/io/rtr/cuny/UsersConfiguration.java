package io.rtr.cuny;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class UsersConfiguration extends Configuration {
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
}
