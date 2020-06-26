package dev.schertel.domain;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReviewerTest {

    @Nested
    public class Builder {
        private Reviewer.Builder builder;

        @BeforeEach
        void setUp() {
            builder = new Reviewer.Builder();
        }

        @ParameterizedTest(name = "{index} \"{0}\" is a invalid value")
        @NullAndEmptySource
        @ValueSource(strings = {"  "})
        void invalidUsername(String username) {
            // Given

            // When
            Exception exception = assertThrows(
                    RuntimeException.class,
                    () -> builder.withUsername(username).withName("name").withEmail("a@a.com").build()
            );

            // Then
            assertThat(exception, instanceOf(NullPointerException.class));
            assertThat(exception.getMessage(), containsString("username"));
        }

        @ParameterizedTest(name = "{index} \"{0}\" is a invalid value")
        @NullAndEmptySource
        @ValueSource(strings = {"  "})
        void invalidName(String name) {
            // Given

            // When
            Exception exception = assertThrows(
                    RuntimeException.class,
                    () -> builder.withUsername("username").withName(name).withEmail("a@a.com").build()
            );

            // Then
            assertThat(exception, instanceOf(NullPointerException.class));
            assertThat(exception.getMessage(), containsString("name"));
        }

        @ParameterizedTest(name = "{index} \"{0}\" is a invalid value")
        @NullAndEmptySource
        @ValueSource(strings = {"  ", "invalidemail", "invalid@email", "invalid@email..com", "@email.com", "invalid@", "invalidemail.com", "invalid.@email.com"})
        void invalidEmail(String email) {
            // Given

            // When
            Exception exception = assertThrows(
                    RuntimeException.class,
                    () -> builder.withUsername("username").withName("name").withEmail(email).build()
            );

            // Then
            assertThat(exception, instanceOf(RuntimeException.class));
            assertThat(exception.getMessage(), containsString("email"));
        }

        @Test
        void nullBuilder() {
            // Given

            // When
            Exception exception = assertThrows(
                    RuntimeException.class,
                    () -> builder.build()
            );

            // Then
            assertThat(exception, instanceOf(IllegalStateException.class));
            assertThat(exception.getMessage(), containsString("username"));
            assertThat(exception.getMessage(), containsString("name"));
            assertThat(exception.getMessage(), containsString("email"));
        }

        @Test
        void fullBuilder() {
            // Given
            // Given
            builder
                    .withUsername("username")
                    .withName("name")
                    .withEmail("valid@email.com");

            // When
            Reviewer actual = builder.build();

            // Then
            assertThat(actual.getUsername(), equalTo("username"));
            assertThat(actual.getName(), equalTo("name"));
            assertThat(actual.getEmail(), equalTo("valid@email.com"));
        }
    }

    @Nested
    public class Override {
        private final Class<ImmutableReviewer> CLAZZ = ImmutableReviewer.class;

        @Test
        void testToString() {
            ToStringVerifier.forClass(CLAZZ)
                    .verify();
        }

        @Test
        void testEquals() {
            EqualsVerifier.forClass(CLAZZ)
                    .withNonnullFields("username", "name", "email")
                    .verify();
        }
    }
}