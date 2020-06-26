package dev.schertel.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class Reviewer {

    private final String username;
    private final String name;
    private final String email;

    public Reviewer(String username, String name, String email) {
        validate(username, name, email);
        this.username = username;
        this.name = name;
        this.email = email;
    }

    private void validate(String username, String name, String email) {
        Objects.requireNonNull(username, "username");
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(email, "email");


        // https://owasp.org/www-community/OWASP_Validation_Regex_Repository
        final Pattern validEmail = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");

        if (!validEmail.matcher(email).matches()) {
            throw new IllegalArgumentException("email");
        }
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reviewer reviewer = (Reviewer) o;
        return Objects.equals(username, reviewer.username) &&
                Objects.equals(name, reviewer.name) &&
                Objects.equals(email, reviewer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, name, email);
    }

    @Override
    public String toString() {
        return "Reviewer{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
