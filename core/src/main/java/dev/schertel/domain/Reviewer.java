package dev.schertel.domain;

import org.immutables.value.Value;

import java.util.regex.Pattern;

@Value.Immutable
@Value.Style(
        init = "with*",
        visibility = Value.Style.ImplementationVisibility.PACKAGE,
        overshadowImplementation = true)
public interface Reviewer {
    String getUsername();

    String getName();

    String getEmail();

    @Value.Check
    default void check() {
        if (getUsername().isBlank()) {
            throw new NullPointerException("username");
        }
        if (getName().isBlank()) {
            throw new NullPointerException("name");
        }
        if (getEmail().isBlank()) {
            throw new NullPointerException("email");
        }
        // https://owasp.org/www-community/OWASP_Validation_Regex_Repository
        final Pattern validEmail = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");

        if (!validEmail.matcher(getEmail()).matches()) {
            throw new IllegalArgumentException("email");
        }
    }

    class Builder extends ImmutableReviewer.Builder {
    }
}
