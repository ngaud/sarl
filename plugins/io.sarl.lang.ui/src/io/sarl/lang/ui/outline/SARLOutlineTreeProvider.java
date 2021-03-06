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
package io.sarl.lang.ui.outline;

import io.sarl.lang.sarl.Action;
import io.sarl.lang.sarl.ActionSignature;
import io.sarl.lang.sarl.Agent;
import io.sarl.lang.sarl.Attribute;
import io.sarl.lang.sarl.Behavior;
import io.sarl.lang.sarl.BehaviorUnit;
import io.sarl.lang.sarl.Capacity;
import io.sarl.lang.sarl.CapacityUses;
import io.sarl.lang.sarl.Constructor;
import io.sarl.lang.sarl.Event;
import io.sarl.lang.sarl.FeatureContainer;
import io.sarl.lang.sarl.RequiredCapacity;
import io.sarl.lang.sarl.SarlPackage;
import io.sarl.lang.sarl.SarlScript;
import io.sarl.lang.sarl.Skill;
import io.sarl.lang.sarl.TopElement;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.ui.editor.outline.impl.DefaultOutlineTreeProvider;
import org.eclipse.xtext.ui.editor.outline.impl.DocumentRootNode;
import org.eclipse.xtext.ui.editor.outline.impl.EObjectNode;
import org.eclipse.xtext.ui.editor.outline.impl.EStructuralFeatureNode;

import com.google.common.base.Strings;

/**
 * Customization of the default outline structure.
 *
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 * @see "http://www.eclipse.org/Xtext/documentation.html#outline"
 */
public class SARLOutlineTreeProvider extends DefaultOutlineTreeProvider {

	/** Create a node for the SARL script.
	 *
	 * @param parentNode - the parent node.
	 * @param modelElement - the feature container for which a node should be created.
	 */
	protected void _createChildren(DocumentRootNode parentNode, SarlScript modelElement) {
		if (!Strings.isNullOrEmpty(modelElement.getName())) {
			createEStructuralFeatureNode(
					parentNode, modelElement,
					SarlPackage.Literals.SARL_SCRIPT__NAME,
					this.imageDispatcher.invoke(getClass().getPackage()),
					// Do not use the text dispatcher below for avoiding to obtain
					// the filename of the script.
					modelElement.getName(),
					true);
		}
		if (modelElement.getImportSection() != null && !modelElement.getImportSection().getImportDeclarations().isEmpty()) {
			createNode(parentNode, modelElement.getImportSection());
		}
		for (TopElement topElement : modelElement.getElements()) {
			createNode(parentNode, topElement);
		}
	}

	/** Create a node for the given feature container.
	 *
	 * @param parentNode - the parent node.
	 * @param modelElement - the feature container for which a node should be created.
	 */
	protected void _createNode(DocumentRootNode parentNode, FeatureContainer modelElement) {
		EStructuralFeatureNode elementNode = createEStructuralFeatureNode(
				parentNode,
				modelElement,
				SarlPackage.Literals.NAMED_ELEMENT__NAME,
				this.imageDispatcher.invoke(modelElement),
				this.textDispatcher.invoke(modelElement),
				modelElement.getFeatures().isEmpty());
		if (!modelElement.getFeatures().isEmpty()) {
			EObjectNode capacityUseNode = null;
			EObjectNode capacityRequirementNode = null;

			for (EObject feature : modelElement.getFeatures()) {
				if (feature instanceof Attribute) {
					createNode(elementNode, feature);
				} else if (feature instanceof Action) {
					createNode(elementNode, feature);
				} else if (feature instanceof ActionSignature) {
					createNode(elementNode, feature);
				} else if (feature instanceof BehaviorUnit) {
					createNode(elementNode, feature);
				} else if (feature instanceof Constructor) {
					createNode(elementNode, feature);
				} else if (feature instanceof CapacityUses) {
					capacityUseNode = createCapacityUseNode(elementNode, feature, capacityUseNode);
				} else if (feature instanceof RequiredCapacity) {
					capacityRequirementNode = createRequiredCapacityNode(elementNode, feature, capacityRequirementNode);
				}
			}
		}
	}

	private EObjectNode createCapacityUseNode(EStructuralFeatureNode elementNode, EObject feature,
			EObjectNode oldCapacityUseNode) {
		EObjectNode capacityUseNode = oldCapacityUseNode;
		if (capacityUseNode == null) {
			capacityUseNode = createEObjectNode(
					elementNode, feature,
					this.imageDispatcher.invoke(feature),
					this.textDispatcher.invoke(feature),
					false);
		}
		for (JvmParameterizedTypeReference item : ((CapacityUses) feature).getCapacitiesUsed()) {
			createEObjectNode(
					capacityUseNode, item,
					this.imageDispatcher.invoke(item),
					this.textDispatcher.invoke(item),
					true);
		}
		return capacityUseNode;
	}

	private EObjectNode createRequiredCapacityNode(EStructuralFeatureNode elementNode, EObject feature,
			EObjectNode oldCapacityRequirementNode) {
		EObjectNode capacityRequirementNode = oldCapacityRequirementNode;
		if (capacityRequirementNode == null) {
			capacityRequirementNode = createEObjectNode(
					elementNode, feature,
					this.imageDispatcher.invoke(feature),
					this.textDispatcher.invoke(feature),
					false);
		}
		for (JvmParameterizedTypeReference item : ((RequiredCapacity) feature).getRequiredCapacities()) {
			createEObjectNode(
					capacityRequirementNode, item,
					this.imageDispatcher.invoke(item),
					this.textDispatcher.invoke(item),
					true);
		}
		return capacityRequirementNode;
	}

	/** Replies if the agent element is a leaf in the outline.
	 *
	 * @param modelElement - the model element.
	 * @return <code>true</code> if it is a leaf, <code>false</code> otherwise.
	 */
	@SuppressWarnings("static-method")
	protected boolean _isLeaf(Agent modelElement) {
		return modelElement.getFeatures().isEmpty();
	}

	/** Replies if the capacity element is a leaf in the outline.
	 *
	 * @param modelElement - the model element.
	 * @return <code>true</code> if it is a leaf, <code>false</code> otherwise.
	 */
	@SuppressWarnings("static-method")
	protected boolean _isLeaf(Capacity modelElement) {
		return modelElement.getFeatures().isEmpty();
	}

	/** Replies if the skill element is a leaf in the outline.
	 *
	 * @param modelElement - the model element.
	 * @return <code>true</code> if it is a leaf, <code>false</code> otherwise.
	 */
	@SuppressWarnings("static-method")
	protected boolean _isLeaf(Skill modelElement) {
		return modelElement.getFeatures().isEmpty();
	}

	/** Replies if the event element is a leaf in the outline.
	 *
	 * @param modelElement - the model element.
	 * @return <code>true</code> if it is a leaf, <code>false</code> otherwise.
	 */
	@SuppressWarnings("static-method")
	protected boolean _isLeaf(Event modelElement) {
		return modelElement.getFeatures().isEmpty();
	}

	/** Replies if the behavior element is a leaf in the outline.
	 *
	 * @param modelElement - the model element.
	 * @return <code>true</code> if it is a leaf, <code>false</code> otherwise.
	 */
	@SuppressWarnings("static-method")
	protected boolean _isLeaf(Behavior modelElement) {
		return modelElement.getFeatures().isEmpty();
	}

	/** Replies if the action element is a leaf in the outline.
	 *
	 * @param modelElement - the model element.
	 * @return <code>true</code> if it is a leaf, <code>false</code> otherwise.
	 */
	@SuppressWarnings("static-method")
	protected boolean _isLeaf(Action modelElement) {
		return true;
	}

	/** Replies if the action signature element is a leaf in the outline.
	 *
	 * @param modelElement - the model element.
	 * @return <code>true</code> if it is a leaf, <code>false</code> otherwise.
	 */
	@SuppressWarnings("static-method")
	protected boolean _isLeaf(ActionSignature modelElement) {
		return true;
	}

	/** Replies if the constructor element is a leaf in the outline.
	 *
	 * @param modelElement - the model element.
	 * @return <code>true</code> if it is a leaf, <code>false</code> otherwise.
	 */
	@SuppressWarnings("static-method")
	protected boolean _isLeaf(Constructor modelElement) {
		return true;
	}

	/** Replies if the behabior unit element is a leaf in the outline.
	 *
	 * @param modelElement - the model element.
	 * @return <code>true</code> if it is a leaf, <code>false</code> otherwise.
	 */
	@SuppressWarnings("static-method")
	protected boolean _isLeaf(BehaviorUnit modelElement) {
		return true;
	}

	/** Replies if the attribute element is a leaf in the outline.
	 *
	 * @param modelElement - the model element.
	 * @return <code>true</code> if it is a leaf, <code>false</code> otherwise.
	 */
	@SuppressWarnings("static-method")
	protected boolean _isLeaf(Attribute modelElement) {
		return true;
	}

}
