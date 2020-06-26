package dev.schertel.domain;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ReviewerTest {

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