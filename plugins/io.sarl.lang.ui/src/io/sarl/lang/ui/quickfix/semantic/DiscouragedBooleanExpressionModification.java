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
package io.sarl.lang.ui.quickfix.semantic;

import io.sarl.lang.ui.quickfix.SARLQuickfixProvider;
import io.sarl.lang.validation.IssueCodes;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.edit.IModificationContext;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.validation.Issue;

/**
 * Quick fixes for {@link IssueCodes#DISCOURAGED_BOOLEAN_EXPRESSION}.
 *
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
public final class DiscouragedBooleanExpressionModification extends SARLSemanticModification {

	/** Create the quick fix if needed.
	 *
	 * @param provider - the quick fix provider.
	 * @param issue - the issue to fix.
	 * @param acceptor - the quick fix acceptor.
	 */
	public static void accept(SARLQuickfixProvider provider, Issue issue, IssueResolutionAcceptor acceptor) {
		DiscouragedBooleanExpressionModification modification = new DiscouragedBooleanExpressionModification();
		modification.setIssue(issue);
		modification.setTools(provider);
		acceptor.accept(issue,
				Messages.SARLQuickfixProvider_9,
				Messages.SARLQuickfixProvider_9,
				null,
				modification);
	}

	@Override
	public void apply(EObject element, IModificationContext context) throws Exception {
		IXtextDocument document = context.getXtextDocument();
		getTools().removeBetweenSeparators(getIssue(), document, "[", "]"); //$NON-NLS-1$//$NON-NLS-2$
	}

}
