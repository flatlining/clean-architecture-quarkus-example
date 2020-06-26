package dev.schertel.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RepositoryNameTest {

    @ParameterizedTest(name = "{index} \"{0}\" is a invalid value")
    @NullAndEmptySource
    @ValueSource(strings = {"  "})
    void ownerMustExist(String owner) {
        // Given

        // When
        Exception exception = assertThrows(
                RuntimeException.class,
                () -> new RepositoryName(owner, "name")
        );

        // Then
        assertThat(exception, instanceOf(NullPointerException.class));
        assertThat(exception.getMessage(), containsString("owner"));
    }

    @ParameterizedTest(name = "{index} \"{0}\" is a invalid value")
    @NullAndEmptySource
    @ValueSource(strings = {"  "})
    void nameMustExist(String name) {
        // Given

        // When
        Exception exception = assertThrows(
                RuntimeException.class,
                () -> new RepositoryName("owner", name)
        );

        // Then
        assertThat(exception, instanceOf(NullPointerException.class));
        assertThat(exception.getMessage(), containsString("name"));
    }

    @Test
    void nullConstructor() {
        // Given

        // When
        Exception exception = assertThrows(
                RuntimeException.class,
                () -> new RepositoryName(null, null)
        );

        // Then
        assertThat(exception, instanceOf(NullPointerException.class));
        assertThat(exception.getMessage(), not(blankString()));
    }

    @Test
    void fullConstructor() {
        // Given

        // When
        RepositoryName actual = new RepositoryName("owner", "name");

        // Then
        assertThat(actual.getOwner(), equalTo("owner"));
        assertThat(actual.getName(), equalTo("name"));
        assertThat(actual.getFullName(), equalTo("owner/name"));
    }
}
