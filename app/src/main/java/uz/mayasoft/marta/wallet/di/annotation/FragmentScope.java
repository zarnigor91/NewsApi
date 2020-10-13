/*
 *   Copyright (c) 2020 Mayasoft LLC, UZ
 *         https://marta.uz
 *
 *        All rights reserved
 */

package uz.mayasoft.marta.wallet.di.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * @author Nodir Badriddinov
 * @since 4/18/20.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentScope {}
