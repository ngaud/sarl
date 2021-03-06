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
package io.sarl.lang.genmodel;


import java.lang.ref.WeakReference;

import io.sarl.lang.sarl.SarlScript;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.util.TypeReferences;
import org.eclipse.xtext.xbase.compiler.ImportManager;
import org.eclipse.xtext.xtype.XImportDeclaration;
import org.eclipse.xtext.xtype.XImportSection;
import org.eclipse.xtext.xtype.XtypeFactory;

/** Describes a generated code.
 *
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
public class GeneratedCode {

	private final ImportManager importManager = new ImportManager();
	private final SarlScript script;
	private final ResourceSet resourceSet;
	private final TypeReferences typeReferences;
	private final WeakReference<SARLCodeGenerator> generator;

	/**
	 * @param generator - the SARL code generator;
	 * @param script - the root Ecore element.
	 * @param resourceSet - the resource set in which the script should be generated.
	 * @param typeReferences - the accessor for exisiting types.
	 */
	GeneratedCode(SARLCodeGenerator generator, SarlScript script,
			ResourceSet resourceSet, TypeReferences typeReferences) {
		this.generator = new WeakReference<>(generator);
		this.script = script;
		this.resourceSet = resourceSet;
		this.typeReferences = typeReferences;
	}

	/** Replies the resource set in which the generated code should be generated.
	 *
	 * @return the resource set of the SARL script.
	 */
	public ResourceSet getResourceSet() {
		return this.resourceSet;
	}

	/** Replies the SARL code generator that had built this code.
	 *
	 * @return the SARL code generator.
	 */
	public SARLCodeGenerator getCodeGenerator() {
		return this.generator.get();
	}

	/** Replies the SARL script.
	 *
	 * @return the SARL script.
	 */
	public SarlScript getSarlScript() {
		return this.script;
	}

	/** Replies the import manager.
	 *
	 * @return the import manager.
	 */
	public ImportManager getImportManager() {
		return this.importManager;
	}

	/** Finialize the script.
	 *
	 * The finalization includes: <ul>
	 * <li>The import section is created.</li>
	 * </ul>
	 */
	public void finalizeScript() {
		XImportSection importSection = this.script.getImportSection();
		for (String importName : this.importManager.getImports()) {
			XImportDeclaration declaration = XtypeFactory.eINSTANCE.createXImportDeclaration();
			JvmType type = this.typeReferences.findDeclaredType(importName, this.script);
			if (type instanceof JvmDeclaredType) {
				declaration.setImportedType((JvmDeclaredType) type);
				if (importSection == null) {
					importSection = XtypeFactory.eINSTANCE.createXImportSection();
					this.script.setImportSection(importSection);
				}
				importSection.getImportDeclarations().add(declaration);
			}
		}
	}

}
