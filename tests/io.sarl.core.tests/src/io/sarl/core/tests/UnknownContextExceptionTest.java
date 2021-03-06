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

import java.util.UUID;

import io.sarl.core.UnknownContextException;
import io.sarl.tests.api.AbstractSarlTest;
import io.sarl.tests.api.Nullable;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
public class UnknownContextExceptionTest extends AbstractSarlTest {

	@Nullable
	private UUID id;

	@Nullable
	private UnknownContextException exception;

	/**
	 */
	@Before
	public void setUp() {
		this.id = UUID.randomUUID();
		this.exception = new UnknownContextException(this.id);
	}

	/**
	 */
	@Test
	public void getUnknownContextID() {
		assertSame(this.id, this.exception.getUnknownContextID());
	}

}
