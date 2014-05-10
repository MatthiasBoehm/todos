package com.greylogix.todos;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.bazaarvoice.dropwizard.assets.AssetsBundleConfiguration;
import com.bazaarvoice.dropwizard.assets.AssetsConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;

public class TodosConfiguration extends Configuration  implements AssetsBundleConfiguration {

	@Valid
    @NotNull
    @JsonProperty
    private final AssetsConfiguration assets                = new AssetsConfiguration();

	@Override
	public AssetsConfiguration getAssetsConfiguration() {
		
		return assets;
	}
	
}
