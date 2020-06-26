package dev.schertel.domain;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import java.util.Objects;

@Value.Immutable
@Value.Style(
        init = "with*",
        visibility = ImplementationVisibility.PACKAGE,
        overshadowImplementation = true)
public interface RepositoryName {
    String getOwner();

    String getName();

    @Value.Check
    default void check() {
        Objects.requireNonNull(getOwner(), "owner");
        if (getOwner().isBlank()) {
            throw new NullPointerException("owner");
        }
        Objects.requireNonNull(getName(), "name");
        if (getName().isBlank()) {
            throw new NullPointerException("name");
        }
    }

    default String getFullname() {
        return String.format("%s/%s", getOwner(), getName());
    }

    class Builder extends ImmutableRepositoryName.Builder {
    }
}
