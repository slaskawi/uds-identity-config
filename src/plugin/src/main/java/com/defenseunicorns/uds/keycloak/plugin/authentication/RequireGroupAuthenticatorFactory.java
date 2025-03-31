/*
 * Copyright 2024 Defense Unicorns
 * SPDX-License-Identifier: AGPL-3.0-or-later OR LicenseRef-Defense-Unicorns-Commercial
 */

package com.defenseunicorns.uds.keycloak.plugin.authentication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.keycloak.Config;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;

import static org.keycloak.provider.ProviderConfigProperty.STRING_TYPE;

public class RequireGroupAuthenticatorFactory implements AuthenticatorFactory {

    /**
     * provider id variable.
     */
    public static final String PROVIDER_ID = "uds-group-restriction";

    /**
     * group authenticator variable.
     */
    public static final RequireGroupAuthenticator GROUP_AUTHENTICATOR = new RequireGroupAuthenticator();

    public static final String TOC_PER_SESSION_CONFIG_NAME = "toc-per-session";

    protected static final List<ProviderConfigProperty> configProperties;

    static {
        ProviderConfigProperty tocPerSession = new ProviderConfigProperty();
        tocPerSession.setType(ProviderConfigProperty.BOOLEAN_TYPE);
        tocPerSession.setName(TOC_PER_SESSION_CONFIG_NAME);
        tocPerSession.setLabel("Display TOC per session");
        tocPerSession.setDefaultValue(Boolean.toString(true));
        tocPerSession.setHelpText("Setting this to true will display the TOC only once per session across multiple devices and tabs. Setting this to false will prompt TOC on every login.");
        configProperties = Arrays.asList(tocPerSession);
    }

    /**
     * requirement choices variable.
     */
    private static final AuthenticationExecutionModel.Requirement[] REQUIREMENT_CHOICES = {
            AuthenticationExecutionModel.Requirement.REQUIRED
    };

    /**
     * This implementation is not intended to be overridden.
     */
    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    /**
     * This implementation is not intended to be overridden.
     */
    @Override
    public Authenticator create(final KeycloakSession session) {
        return GROUP_AUTHENTICATOR;
    }

    @Override
    public void init(final Config.Scope scope) {
        // no implementation needed here
    }

    @Override
    public void postInit(final KeycloakSessionFactory keycloakSessionFactory) {
        // no implementation needed here
    }

    @Override
    public void close() {
        // no implementation needed here
    }

    @Override
    public String getDisplayType() {
        return "UDS Operator Group Authentication Validation";
    }

    @Override
    public String getReferenceCategory() {
        return null;
    }

    @Override
    public boolean isConfigurable() {
        return true;
    }

    @Override
    public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
        return REQUIREMENT_CHOICES;
    }

    @Override
    public boolean isUserSetupAllowed() {
        return true;
    }

    @Override
    public String getHelpText() {
        return null;
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return configProperties;
    }

}