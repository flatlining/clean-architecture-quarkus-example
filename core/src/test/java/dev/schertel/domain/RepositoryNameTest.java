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

class RepositoryNameTest {

    @Nested
    public class Builder {
        private RepositoryName.Builder builder;

        @BeforeEach
        void setUp() {
            builder = new RepositoryName.Builder();
        }

        @ParameterizedTest(name = "{index} \"{0}\" is a invalid value")
        @NullAndEmptySource
        @ValueSource(strings = {"  "})
        void ownerMustExist(String owner) {
            // Given

            // When
            Exception exception = assertThrows(
                    RuntimeException.class,
                    () -> builder.withOwner(owner).withName("name").build()
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
                    () -> builder.withOwner("owner").withName(name).build()
            );

            // Then
            assertThat(exception, instanceOf(NullPointerException.class));
            assertThat(exception.getMessage(), containsString("name"));
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
            assertThat(exception.getMessage(), containsString("owner"));
            assertThat(exception.getMessage(), containsString("name"));
        }

        @Test
        void fullBuilder() {
            // Given
            // Given
            builder
                    .withOwner("owner")
                    .withName("name");

            // When
            RepositoryName actual = builder.build();

            // Then
            assertThat(actual.getOwner(), equalTo("owner"));
            assertThat(actual.getName(), equalTo("name"));
            //assertThat(actual.getFullName(), equalTo("owner/name"));
        }
    }

    @Nested
    public class Override {
        private final Class<ImmutableRepositoryName> CLAZZ = ImmutableRepositoryName.class;

        @Test
        void testToString() {
            ToStringVerifier.forClass(CLAZZ)
                    .verify();
        }

        @Test
        void testEquals() {
            EqualsVerifier.forClass(CLAZZ)
                    .withNonnullFields("owner", "name")
                    .verify();
        }
    }
}
