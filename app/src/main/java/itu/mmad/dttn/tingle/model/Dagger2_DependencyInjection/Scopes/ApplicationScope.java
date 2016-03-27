package itu.mmad.dttn.tingle.model.Dagger2_DependencyInjection.Scopes;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import javax.inject.Qualifier;

/**
 * Used as a custom scope annotation.
 */
@Qualifier @Retention(RUNTIME)
public @interface  ApplicationScope {

}
