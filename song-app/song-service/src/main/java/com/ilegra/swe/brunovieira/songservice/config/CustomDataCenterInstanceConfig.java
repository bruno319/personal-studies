package com.ilegra.swe.brunovieira.songservice.config;

import com.netflix.appinfo.AbstractInstanceConfig;
import com.netflix.appinfo.DataCenterInfo;
import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.discovery.CommonConstants;
import com.netflix.discovery.internal.util.Archaius1Utils;
import org.apache.commons.configuration.Configuration;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.ilegra.swe.brunovieira.songservice.config.PropertyBasedInstanceConfigConstants.*;

public class CustomDataCenterInstanceConfig extends AbstractInstanceConfig {

    private final String namespace;
    private final DynamicPropertyFactory configInstance;
    private String appGrpNameFromEnv;

    public CustomDataCenterInstanceConfig() {
        this(CommonConstants.DEFAULT_CONFIG_NAMESPACE);
    }

    public CustomDataCenterInstanceConfig(String namespace) {
        this(namespace, () -> DataCenterInfo.Name.MyOwn);
    }

    public CustomDataCenterInstanceConfig(String namespace, DataCenterInfo info) {
        super(info);

        this.namespace = namespace.endsWith(".")
                ? namespace
                : namespace + ".";

        appGrpNameFromEnv = ConfigurationManager.getConfigInstance()
                .getString(FALLBACK_APP_GROUP_KEY, PropertyBasedInstanceConfigConstants.Values.UNKNOWN_APPLICATION);

        this.configInstance = Archaius1Utils.initConfig("application");
    }

    @Override
    public boolean isInstanceEnabledOnit() {
        return configInstance.getBooleanProperty(namespace + TRAFFIC_ENABLED_ON_INIT_KEY,
                super.isInstanceEnabledOnit()).get();
    }

    @Override
    public int getNonSecurePort() {
        return configInstance.getIntProperty(namespace + PORT_KEY, super.getNonSecurePort()).get();
    }

    @Override
    public int getSecurePort() {
        return configInstance.getIntProperty(namespace + SECURE_PORT_KEY, super.getSecurePort()).get();
    }

    @Override
    public boolean isNonSecurePortEnabled() {
        return configInstance.getBooleanProperty(namespace + PORT_ENABLED_KEY, super.isNonSecurePortEnabled()).get();
    }

    @Override
    public boolean getSecurePortEnabled() {
        return configInstance.getBooleanProperty(namespace + SECURE_PORT_ENABLED_KEY,
                super.getSecurePortEnabled()).get();
    }

    @Override
    public int getLeaseRenewalIntervalInSeconds() {
        return configInstance.getIntProperty(namespace + LEASE_RENEWAL_INTERVAL_KEY,
                super.getLeaseRenewalIntervalInSeconds()).get();
    }

    @Override
    public int getLeaseExpirationDurationInSeconds() {
        return configInstance.getIntProperty(namespace + LEASE_EXPIRATION_DURATION_KEY,
                super.getLeaseExpirationDurationInSeconds()).get();
    }

    @Override
    public String getVirtualHostName() {
        if (this.isNonSecurePortEnabled()) {
            return configInstance.getStringProperty(namespace + VIRTUAL_HOSTNAME_KEY,
                    super.getVirtualHostName()).get();
        } else {
            return null;
        }
    }

    @Override
    public String getSecureVirtualHostName() {
        if (this.getSecurePortEnabled()) {
            return configInstance.getStringProperty(namespace + SECURE_VIRTUAL_HOSTNAME_KEY,
                    super.getSecureVirtualHostName()).get();
        } else {
            return null;
        }
    }

    @Override
    public String getASGName() {
        return configInstance.getStringProperty(namespace + ASG_NAME_KEY, super.getASGName()).get();
    }

    @Override
    public Map<String, String> getMetadataMap() {
        String metadataNamespace = namespace + INSTANCE_METADATA_PREFIX + ".";
        Map<String, String> metadataMap = new LinkedHashMap<>();
        Configuration config = (Configuration) configInstance.getBackingConfigurationSource();
        String subsetPrefix = metadataNamespace.charAt(metadataNamespace.length() - 1) == '.'
                ? metadataNamespace.substring(0, metadataNamespace.length() - 1)
                : metadataNamespace;
        for (Iterator<String> iter = config.subset(subsetPrefix).getKeys(); iter.hasNext(); ) {
            String key = iter.next();
            String value = config.getString(subsetPrefix + "." + key);
            metadataMap.put(key, value);
        }
        return metadataMap;
    }

    @Override
    public String getInstanceId() {
        String result = configInstance.getStringProperty(namespace + INSTANCE_ID_KEY, null).get();
        return result == null ? null : result.trim();
    }

    @Override
    public String getAppname() {
        return configInstance.getStringProperty(namespace + APP_NAME_KEY, Values.UNKNOWN_APPLICATION).get().trim();
    }

    @Override
    public String getAppGroupName() {
        return configInstance.getStringProperty(namespace + APP_GROUP_KEY, appGrpNameFromEnv).get().trim();
    }

    public String getIpAddress() {
        return super.getIpAddress();
    }


    @Override
    public String getStatusPageUrlPath() {
        return configInstance.getStringProperty(namespace + STATUS_PAGE_URL_PATH_KEY,
                Values.DEFAULT_STATUSPAGE_URLPATH).get();
    }

    @Override
    public String getStatusPageUrl() {
        return configInstance.getStringProperty(namespace + STATUS_PAGE_URL_KEY, null)
                .get();
    }


    @Override
    public String getHomePageUrlPath() {
        return configInstance.getStringProperty(namespace + HOME_PAGE_URL_PATH_KEY,
                Values.DEFAULT_HOMEPAGE_URLPATH).get();
    }

    @Override
    public String getHomePageUrl() {
        return configInstance.getStringProperty(namespace + HOME_PAGE_URL_KEY, null)
                .get();
    }

    @Override
    public String getHealthCheckUrlPath() {
        return configInstance.getStringProperty(namespace + HEALTHCHECK_URL_PATH_KEY,
                Values.DEFAULT_HEALTHCHECK_URLPATH).get();
    }

    @Override
    public String getHealthCheckUrl() {
        return configInstance.getStringProperty(namespace + HEALTHCHECK_URL_KEY, null)
                .get();
    }

    @Override
    public String getSecureHealthCheckUrl() {
        return configInstance.getStringProperty(namespace + SECURE_HEALTHCHECK_URL_KEY,
                null).get();
    }

    @Override
    public String[] getDefaultAddressResolutionOrder() {
        String result = configInstance.getStringProperty(namespace + DEFAULT_ADDRESS_RESOLUTION_ORDER_KEY, null).get();
        return result == null ? new String[0] : result.split(",");
    }

    @Override
    public String getNamespace() {
        return this.namespace;
    }
}
