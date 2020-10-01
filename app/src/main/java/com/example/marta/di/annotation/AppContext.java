/*
 *   Copyright (c) 2020 Mayasoft LLC, UZ
 *         https://marta.uz
 *
 *        All rights reserved
 */

package com.example.marta.di.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;

/**

 * @since 2020
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface AppContext {}
