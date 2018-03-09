package com.amdmeetup.demodocs.config;

import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.snippet.Attributes;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ITConfig {
    public static class ConstrainedFields {

        private final ConstraintDescriptions constraintDescriptions;
        private final Class input;

        public ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
            this.input = input;
        }

        public FieldDescriptor withPath(String path, String description) {
            return PayloadDocumentation.fieldWithPath(path).description(description)
                    .attributes(Attributes.key("constraints").value(StringUtils
                            .collectionToDelimitedString(this.constraintDescriptions
                                    .descriptionsForProperty(path), ". ")));
        }

        /**
         * Will populate /src/test/resources/org/springframework/restdocs/templates/asciidoctor/request-fields.snippet
         *
         * @return RequestFieldsSnippet of requested class
         */
        public RequestFieldsSnippet requestFields() {
            List<FieldDescriptor> fields = new ArrayList<>();
            Field[] allFields = input.getDeclaredFields();
            for (Field field : allFields) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    fields.add(withPath(
                            field.getName(), field.getName() + " for "
                                    + field.getDeclaringClass().getSimpleName()
                                    .replaceAll("(.)(\\p{Upper})", "$1 $2")));
                }
            }
            return PayloadDocumentation.requestFields(fields);
        }
    }
}