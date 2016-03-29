package itu.mmad.dttn.tingle.model.Dagger2_DependencyInjection.Qualifiers;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Used to qualify SQL repository. Used because there are multiple types of repositories
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface QSQLRepository {
}
