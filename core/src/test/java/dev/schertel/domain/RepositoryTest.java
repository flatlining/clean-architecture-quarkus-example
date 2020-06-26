package dev.schertel.domain;

import com.jparams.verifier.tostring.ToStringVerifier;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.net.URI;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(RandomBeansExtension.class)
class RepositoryTest {

    @Nested
    public class Builder {
        private Repository.Builder builder;

        @BeforeEach
        void setUp() {
            builder = new Repository.Builder();
        }

        @Test
        void invalidName(@Random URI uri, @Random(type = ImmutableReviewer.class) List<Reviewer> reviewers) {
            // Given

            // When
            Exception exception = assertThrows(
                    RuntimeException.class,
                    () -> builder.withName(null).withUri(uri).withReviewers(reviewers).build()
            );

            // Then
            assertThat(exception, instanceOf(NullPointerException.class));
            assertThat(exception.getMessage(), containsString("name"));
        }

        @Test
        void invalidUri(@Random ImmutableRepositoryName name, @Random(type = ImmutableReviewer.class) List<Reviewer> reviewers) {
            // Given

            // When
            Exception exception = assertThrows(
                    RuntimeException.class,
                    () -> builder.withName(name).withUri(null).withReviewers(reviewers).build()
            );

            // Then
            assertThat(exception, instanceOf(NullPointerException.class));
            assertThat(exception.getMessage(), containsString("uri"));
        }

        @Test
        void invalidReviewers(@Random ImmutableRepositoryName name, @Random URI uri) {
            // Given

            // When
            Exception exception = assertThrows(
                    RuntimeException.class,
                    () -> builder.withName(name).withUri(uri).withReviewers(null).build()
            );

            // Then
            assertThat(exception, instanceOf(NullPointerException.class));
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
            assertThat(exception.getMessage(), containsString("name"));
            assertThat(exception.getMessage(), containsString("uri"));
        }

        @Test
        void fullBuilder(@Random ImmutableRepositoryName name, @Random URI uri, @Random(type = ImmutableReviewer.class) List<Reviewer> reviewers) {
            // Given
            builder
                    .withName(name)
                    .withUri(uri)
                    .withReviewers(reviewers);

            // When
            Repository actual = builder.build();

            // Then
            assertThat(actual.getName(), equalTo(name));
            assertThat(actual.getUri(), equalTo(uri));
            assertThat(actual.getReviewers(), equalTo(reviewers));
        }
    }

    @Nested
    public class Override {
        private final Class<ImmutableRepository> CLAZZ = ImmutableRepository.class;

        @Test
        void testToString() {
            ToStringVerifier.forClass(CLAZZ)
                    .verify();
        }

        @Test
        void testEquals() {
            EqualsVerifier.forClass(CLAZZ)
                    .withNonnullFields("name", "uri", "reviewers")
                    .verify();
        }
    }
}
