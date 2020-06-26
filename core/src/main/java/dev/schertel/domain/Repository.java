package dev.schertel.domain;

import org.immutables.value.Value;

import java.net.URI;
import java.util.List;

@Value.Immutable
@Value.Style(
        init = "with*",
        visibility = Value.Style.ImplementationVisibility.PACKAGE,
        overshadowImplementation = true)
public interface Repository {

    public RepositoryName getName();

    public URI getUri();

    public List<Reviewer> getReviewers();

    @Value.Check
    default void check() {

    }

    class Builder extends ImmutableRepository.Builder {
    }
}
