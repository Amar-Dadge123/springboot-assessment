package com.example.springboot.assessment.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SwaggerConfigTest {

    @Test
    void testCustomOpenAPI_ReturnsOpenAPIInstance() {
        // Given
        SwaggerConfig swaggerConfig = new SwaggerConfig();

        // When
        OpenAPI openAPI = swaggerConfig.customOpenAPI();

        // Then
        assertNotNull(openAPI);
        assertInstanceOf(OpenAPI.class, openAPI);
    }

    @Test
    void testCustomOpenAPI_InfoIsNotNull() {
        SwaggerConfig swaggerConfig = new SwaggerConfig();
        OpenAPI openAPI = swaggerConfig.customOpenAPI();

        assertNotNull(openAPI.getInfo());
    }

    @Test
    void testCustomOpenAPI_InfoTitle() {
        SwaggerConfig swaggerConfig = new SwaggerConfig();
        OpenAPI openAPI = swaggerConfig.customOpenAPI();
        Info info = openAPI.getInfo();

        assertEquals("Book Management API", info.getTitle());
    }

    @Test
    void testCustomOpenAPI_InfoVersion() {
        SwaggerConfig swaggerConfig = new SwaggerConfig();
        OpenAPI openAPI = swaggerConfig.customOpenAPI();
        Info info = openAPI.getInfo();

        assertEquals("1.0.0", info.getVersion());
    }

    @Test
    void testCustomOpenAPI_InfoDescription() {
        SwaggerConfig swaggerConfig = new SwaggerConfig();
        OpenAPI openAPI = swaggerConfig.customOpenAPI();
        Info info = openAPI.getInfo();

        assertEquals("API for managing books", info.getDescription());
    }

    @Test
    void testCustomOpenAPI_ContactIsNotNull() {
        SwaggerConfig swaggerConfig = new SwaggerConfig();
        OpenAPI openAPI = swaggerConfig.customOpenAPI();
        Info info = openAPI.getInfo();

        assertNotNull(info.getContact());
    }

    @Test
    void testCustomOpenAPI_ContactName() {
        SwaggerConfig swaggerConfig = new SwaggerConfig();
        OpenAPI openAPI = swaggerConfig.customOpenAPI();
        Contact contact = openAPI.getInfo().getContact();

        assertEquals("Amar Dadge", contact.getName());
    }

    @Test
    void testCustomOpenAPI_ContactEmail() {
        SwaggerConfig swaggerConfig = new SwaggerConfig();
        OpenAPI openAPI = swaggerConfig.customOpenAPI();
        Contact contact = openAPI.getInfo().getContact();

        assertEquals("amar@example.com", contact.getEmail());
    }

    @Test
    void testCustomOpenAPI_LicenseIsNotNull() {
        SwaggerConfig swaggerConfig = new SwaggerConfig();
        OpenAPI openAPI = swaggerConfig.customOpenAPI();
        Info info = openAPI.getInfo();

        assertNotNull(info.getLicense());
    }

    @Test
    void testCustomOpenAPI_LicenseName() {
        SwaggerConfig swaggerConfig = new SwaggerConfig();
        OpenAPI openAPI = swaggerConfig.customOpenAPI();
        License license = openAPI.getInfo().getLicense();

        assertEquals("Apache 2.0", license.getName());
    }

    @Test
    void testCustomOpenAPI_LicenseUrl() {
        SwaggerConfig swaggerConfig = new SwaggerConfig();
        OpenAPI openAPI = swaggerConfig.customOpenAPI();
        License license = openAPI.getInfo().getLicense();

        assertEquals("http://springdoc.org", license.getUrl());
    }

    @Test
    void testCustomOpenAPI_AllProperties() {
        SwaggerConfig swaggerConfig = new SwaggerConfig();
        OpenAPI openAPI = swaggerConfig.customOpenAPI();

        // Test the complete object structure
        assertNotNull(openAPI);

        Info info = openAPI.getInfo();
        assertNotNull(info);
        assertEquals("Book Management API", info.getTitle());
        assertEquals("1.0.0", info.getVersion());
        assertEquals("API for managing books", info.getDescription());

        Contact contact = info.getContact();
        assertNotNull(contact);
        assertEquals("Amar Dadge", contact.getName());
        assertEquals("amar@example.com", contact.getEmail());

        License license = info.getLicense();
        assertNotNull(license);
        assertEquals("Apache 2.0", license.getName());
        assertEquals("http://springdoc.org", license.getUrl());
    }

    @Test
    void testCustomOpenAPI_MultipleCallsReturnDifferentInstances() {
        SwaggerConfig swaggerConfig = new SwaggerConfig();

        OpenAPI openAPI1 = swaggerConfig.customOpenAPI();
        OpenAPI openAPI2 = swaggerConfig.customOpenAPI();

        // They should be different instances (new object each time)
        assertNotSame(openAPI1, openAPI2);

        // But with same content
        assertEquals(openAPI1.getInfo().getTitle(), openAPI2.getInfo().getTitle());
        assertEquals(openAPI1.getInfo().getVersion(), openAPI2.getInfo().getVersion());
    }
}
