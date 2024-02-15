package com.ssl.loans.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "loans")
public record AccountsContactInfoDTO(String message, Map<String, String> contactDetails) { }
