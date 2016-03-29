package itu.mmad.dttn.tingle.model.Dagger2_DependencyInjection.Qualifiers;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import javax.inject.Qualifier;

/**
 * Used to qualify in-memory repository. Used because there are multiple types of repositories
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface QInMemoryRepository {
}

