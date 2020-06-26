package dev.schertel.domain;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Repository {

    private final RepositoryName name;
    private final URI uri;
    private final List<Reviewer> reviewers;

    public Repository(RepositoryName name, URI uri, List<Reviewer> reviewers) {
        validate(name, uri);
        this.name = name;
        this.uri = uri;
        this.reviewers = Objects.requireNonNullElse(reviewers, Collections.emptyList());
    }

    private void validate(RepositoryName name, URI uri) {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(uri, "uri");
    }

    public RepositoryName getName() {
        return name;
    }

    public URI getUri() {
        return uri;
    }

    public List<Reviewer> getReviewers() {
        return reviewers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repository that = (Repository) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(uri, that.uri) &&
                Objects.equals(reviewers, that.reviewers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, uri, reviewers);
    }

    @Override
    public String toString() {
        return "Repository{" +
                "name=" + name +
                ", uri=" + uri +
                ", reviewers=" + reviewers +
                '}';
    }

    public static final class Builder {
        private RepositoryName name;
        private URI uri;
        private List<Reviewer> reviewers;

        private Builder() {
        }

        public static Builder aRepository() {
            return new Builder();
        }

        public Builder withName(RepositoryName name) {
            this.name = name;
            return this;
        }

        public Builder withUri(URI uri) {
            this.uri = uri;
            return this;
        }

        public Builder withReviewers(List<Reviewer> reviewers) {
            this.reviewers = reviewers;
            return this;
        }

        public Repository build() {
            return new Repository(name, uri, reviewers);
        }
    }
}
