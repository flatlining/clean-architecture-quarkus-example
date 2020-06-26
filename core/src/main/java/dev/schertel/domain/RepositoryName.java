package dev.schertel.domain;

import java.util.Objects;

public class RepositoryName {

    private final String owner;
    private final String name;

    public RepositoryName(String owner, String name) {
        validate(owner, name);
        this.owner = owner;
        this.name = name;
    }

    private void validate(String owner, String name) {
        Objects.requireNonNull(owner, "owner");
        Objects.requireNonNull(name, "name");
    }

    public String getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        final StringBuilder fullname = new StringBuilder();
        fullname.append(getOwner()).append("/").append(getName());
        return fullname.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepositoryName that = (RepositoryName) o;
        return Objects.equals(owner, that.owner) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, name);
    }

    @Override
    public String toString() {
        return "RepositoryName{" +
                "owner='" + owner + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public static final class Builder {
        private String owner;
        private String name;

        private Builder() {
        }

        public static Builder aRepositoryName() {
            return new Builder();
        }

        public Builder withOwner(String owner) {
            this.owner = owner;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public RepositoryName build() {
            return new RepositoryName(owner, name);
        }
    }
}
