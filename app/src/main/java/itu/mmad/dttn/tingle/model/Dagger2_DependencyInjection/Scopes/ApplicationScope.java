package itu.mmad.dttn.tingle.model.Dagger2_DependencyInjection.Scopes;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Used as a custom scope annotation.
 */
@Qualifier
@Retention(RUNTIME)
public @interface ApplicationScope
{

}
