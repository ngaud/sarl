/*
 * $Id$
 *
 * SARL is an general-purpose agent programming language.
 * More details on http://www.sarl.io
 *
 * Copyright (C) 2014-2015 Sebastian RODRIGUEZ, Nicolas GAUD, Stéphane GALLAND.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.sarl.core.tests;

import static org.junit.Assert.assertEquals;
import io.sarl.lang.core.AgentContext;
import io.sarl.lang.core.Capacity;
import io.sarl.lang.util.SynchronizedCollection;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

/**
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
public class ExternalContextAccessTest extends AbstractSarlCoreTest<Capacity> {

	/**
	 */
	@Before
	public void setUp() {
		loadSARL("io.sarl.core.ExternalContextAccess", Capacity.class); //$NON-NLS-1$
	}

	/**
	 */
	@Test
	public void memberCount() {
		assertEquals(7, this.type.getDeclaredMethods().length);
	}

	/**
	 */
	@Test
	public void getAllContexts() {
		assertMethod("getAllContexts", SynchronizedCollection.class); //$NON-NLS-1$
	}

	/**
	 */
	@Test
	public void getContext() {
		assertMethod("getContext", AgentContext.class, UUID.class); //$NON-NLS-1$
	}

	/**
	 */
	@Test
	public void join() {
		assertMethod("join", void.class, UUID.class, UUID.class); //$NON-NLS-1$
	}

	/**
	 */
	@Test
	public void leave() {
		assertMethod("leave", void.class, UUID.class); //$NON-NLS-1$
	}

}
