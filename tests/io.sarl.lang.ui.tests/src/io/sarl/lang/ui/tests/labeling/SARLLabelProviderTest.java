/*
 * Copyright 2014 Sebastian RODRIGUEZ, Nicolas GAUD, Stéphane GALLAND.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.sarl.lang.ui.tests.labeling;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
import io.sarl.lang.sarl.RequiredCapacity;
import io.sarl.lang.sarl.SarlScript;
import io.sarl.lang.sarl.Skill;
import io.sarl.lang.ui.labeling.SARLLabelProvider;
import io.sarl.tests.api.AbstractSarlUiTest;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.jdt.ui.JavaElementImageDescriptor;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.inject.Inject;

/**
 * @author $Author: sgalland$
 * @version $Name$ $Revision$ $Date$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
public class SARLLabelProviderTest extends AbstractSarlUiTest {

	private static final String PACKAGE_STATEMENT = "package io.sarl.lang.ui.tests.labeling\n"; //$NON-NLS-1$

	@Inject
	private SARLLabelProvider provider;

	private static String filename(String basename) {
		return pathStr(
				"io", "sarl", //$NON-NLS-1$//$NON-NLS-2$
				"lang", "ui", //$NON-NLS-1$//$NON-NLS-2$
				"tests", "labeling", //$NON-NLS-1$//$NON-NLS-2$
				basename);
	}

	/**
	 */
	@Test
	public void getImageDescriptorPackage() {
		assertBundleImage(
				"packd_obj.gif", //$NON-NLS-1$
				this.provider.getImageDescriptor(getClass().getPackage()));
	}

	/**
	 */
	@Test
	public void getImageDescriptorSarlScript() {
		assertBundleImage(
				"sarl-file.png", //$NON-NLS-1$
				this.provider.getImageDescriptor(Mockito.mock(SarlScript.class)));
	}

	/**
	 */
	@Test
	public void getImageDescriptorAgent() {
		assertBundleImage(
				"sarl-agent.png", //$NON-NLS-1$
				this.provider.getImageDescriptor(Mockito.mock(Agent.class)));
	}

	/**
	 */
	@Test
	public void getImageDescriptorEvent() {
		assertBundleImage(
				"sarl-event.png", //$NON-NLS-1$
				this.provider.getImageDescriptor(Mockito.mock(Event.class)));
	}

	/**
	 */
	@Test
	public void getImageDescriptorCapacity() {
		assertBundleImage(
				"sarl-capacity.png", //$NON-NLS-1$
				this.provider.getImageDescriptor(Mockito.mock(Capacity.class)));
	}

	/**
	 */
	@Test
	public void getImageDescriptorSkill() {
		assertBundleImage(
				"sarl-skill.png", //$NON-NLS-1$
				this.provider.getImageDescriptor(Mockito.mock(Skill.class)));
	}

	/**
	 */
	@Test
	public void getImageDescriptorBehavior() {
		assertBundleImage(
				"sarl-behavior.png", //$NON-NLS-1$
				this.provider.getImageDescriptor(Mockito.mock(Behavior.class)));
	}

	/**
	 */
	@Test
	public void getImageDescriptorAttribute() {
		assertJdtImage(
				JavaPluginImages.DESC_FIELD_PROTECTED, JavaElementImageDescriptor.FINAL,
				this.provider.getImageDescriptor(Mockito.mock(Attribute.class)));
	}

	/**
	 */
	@Test
	public void getImageDescriptorConstructor() {
		assertJdtImage(
				JavaPluginImages.DESC_MISC_PUBLIC, JavaElementImageDescriptor.CONSTRUCTOR,
				this.provider.getImageDescriptor(Mockito.mock(Constructor.class)));
	}

	/**
	 */
	@Test
	public void getImageDescriptorAction() {
		assertJdtImage(
				JavaPluginImages.DESC_MISC_PUBLIC, 0,
				this.provider.getImageDescriptor(Mockito.mock(Action.class)));
	}

	/**
	 */
	@Test
	public void getImageDescriptorActionSignature() {
		assertJdtImage(
				JavaPluginImages.DESC_MISC_PUBLIC, JavaElementImageDescriptor.ABSTRACT,
				this.provider.getImageDescriptor(Mockito.mock(ActionSignature.class)));
	}

	/**
	 */
	@Test
	public void getImageDescriptorCapacityUses() {
		assertJdtImage(
				JavaPluginImages.DESC_OBJS_IMPCONT, 0,
				this.provider.getImageDescriptor(Mockito.mock(CapacityUses.class)));
	}

	/**
	 */
	@Test
	public void getImageDescriptorRequiredCapacity() {
		assertJdtImage(
				JavaPluginImages.DESC_OBJS_IMPCONT, 0,
				this.provider.getImageDescriptor(Mockito.mock(RequiredCapacity.class)));
	}

	/**
	 */
	@Test
	public void getImageDescriptorBehaviorUnit() {
		assertBundleImage(
				"sarl-behavior-unit.png",  //$NON-NLS-1$
				this.provider.getImageDescriptor(Mockito.mock(BehaviorUnit.class)));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void getTextJvmParametizedTypeReference_0() throws Exception {
		Agent agent = this.helper.createSARLTopElement(
				filename("textJvmParametizedTypeReference_0"), //$NON-NLS-1$
				Agent.class, 1,
				PACKAGE_STATEMENT
				+ "agent A1 { }\n" //$NON-NLS-1$
				+ "agent A2 extends A1 { }\n"); //$NON-NLS-1$
		this.helper.getValidator().assertNoErrors(agent);
		EList<JvmParameterizedTypeReference> superTypes = agent.getSuperTypes();
		assertNotNull(superTypes);
		assertEquals(1, superTypes.size());
		Object text = this.provider.getText(superTypes.get(0));
		assertNotNull(text);
		assertEquals("A1", text); //$NON-NLS-1$
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void getTextJvmParametizedTypeReference_1() throws Exception {
		Event event = this.helper.createSARLTopElement(
				filename("textJvmParametizedTypeReference_1"), //$NON-NLS-1$
				Event.class,
				PACKAGE_STATEMENT
				+ "event E1 {\n" //$NON-NLS-1$
				+ "var attr : org.eclipse.xtext.xbase.lib.Pair<java.lang.Integer,java.lang.Double>\n" //$NON-NLS-1$
				+ "}"); //$NON-NLS-1$
		this.helper.getValidator().assertNoErrors(event);
		EList<EObject> features = event.getFeatures();
		assertNotNull(features);
		assertEquals(1, features.size());
		EObject eObject = features.get(0);
		assertTrue(eObject instanceof Attribute);
		Attribute attr = (Attribute)eObject;
		JvmTypeReference typeReference = attr.getType();
		assertTrue(typeReference instanceof JvmParameterizedTypeReference);
		JvmParameterizedTypeReference parametizedTypeReference = (JvmParameterizedTypeReference)typeReference;
		Object text = this.provider.getText(parametizedTypeReference);
		assertNotNull(text);
		assertEquals("Pair<Integer, Double>", text); //$NON-NLS-1$
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void getTextJvmTypeReference() throws Exception {
		Agent agent = this.helper.createSARLTopElement(
				filename("textJvmTypeReference"), //$NON-NLS-1$
				Agent.class, 1,
				PACKAGE_STATEMENT
				+ "agent A1 { }\n" //$NON-NLS-1$
				+ "agent A2 extends A1 { }\n"); //$NON-NLS-1$
		this.helper.getValidator().assertNoErrors(agent);
		EList<JvmParameterizedTypeReference> superTypes = agent.getSuperTypes();
		assertNotNull(superTypes);
		assertEquals(1, superTypes.size());
		JvmTypeReference generalReference = superTypes.get(0);
		Object text = this.provider.getText(generalReference);
		assertNotNull(text);
		assertEquals("A1", text); //$NON-NLS-1$
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void getTextSarlScript() throws Exception {
		SarlScript script = this.helper.createSARLScript(
				filename("textSarlScript"), //$NON-NLS-1$
				PACKAGE_STATEMENT);
		this.helper.getValidator().assertNoErrors(script);
		Object text = this.provider.getText(script);
		assertNotNull(text);
		assertEquals("textSarlScript", text); //$NON-NLS-1$
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void getTextAgent() throws Exception {
		Agent agent = this.helper.createSARLTopElement(
				filename("textAgent"), //$NON-NLS-1$
				Agent.class,
				PACKAGE_STATEMENT
				+ "agent A1 { }"); //$NON-NLS-1$
		this.helper.getValidator().assertNoErrors(agent);
		Object text = this.provider.getText(agent);
		assertNotNull(text);
		assertEquals("A1", text); //$NON-NLS-1$
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void getTextEvent() throws Exception {
		Event event = this.helper.createSARLTopElement(
				filename("textEvent"), //$NON-NLS-1$
				Event.class,
				PACKAGE_STATEMENT
				+ "event E1 { }"); //$NON-NLS-1$
		this.helper.getValidator().assertNoErrors(event);
		Object text = this.provider.getText(event);
		assertNotNull(text);
		assertEquals("E1", text); //$NON-NLS-1$
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void getTextCapacity() throws Exception {
		Capacity capacity = this.helper.createSARLTopElement(
				filename("textCapacity"), //$NON-NLS-1$
				Capacity.class,
				PACKAGE_STATEMENT
				+ "capacity C1 { }"); //$NON-NLS-1$
		this.helper.getValidator().assertNoErrors(capacity);
		Object text = this.provider.getText(capacity);
		assertNotNull(text);
		assertEquals("C1", text); //$NON-NLS-1$
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void getTextSkill() throws Exception {
		Skill skill = this.helper.createSARLTopElement(
				filename("textSkill"), //$NON-NLS-1$
				Skill.class, 1,
				PACKAGE_STATEMENT
				+ "capacity C1 { }\n" //$NON-NLS-1$
				+ "skill S1 implements C1 { }"); //$NON-NLS-1$
		this.helper.getValidator().assertNoErrors(skill);
		Object text = this.provider.getText(skill);
		assertNotNull(text);
		assertEquals("S1", text); //$NON-NLS-1$
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void getTextBehavior() throws Exception {
		Behavior behavior = this.helper.createSARLTopElement(
				filename("textBehavior"), //$NON-NLS-1$
				Behavior.class,
				PACKAGE_STATEMENT
				+ "behavior B1 { }"); //$NON-NLS-1$
		this.helper.getValidator().assertNoErrors(behavior);
		Object text = this.provider.getText(behavior);
		assertNotNull(text);
		assertEquals("B1", text); //$NON-NLS-1$
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void getTextAttribute() throws Exception {
		Agent agent = this.helper.createSARLTopElement(
				filename("textAttribute"), //$NON-NLS-1$
				Agent.class,
				PACKAGE_STATEMENT
				+ "agent A1 { var myAttr : boolean }"); //$NON-NLS-1$
		this.helper.getValidator().assertNoErrors(agent);
		Object feature = agent.getFeatures().get(0);
		assertNotNull(feature);
		assertTrue(feature instanceof Attribute);
		Object text = this.provider.getText(feature);
		assertNotNull(text);
		assertEquals("myAttr : boolean", text); //$NON-NLS-1$
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void getTextConstructor() throws Exception {
		Event event = this.helper.createSARLTopElement(
				filename("textConstructor"), //$NON-NLS-1$
				Event.class,
				PACKAGE_STATEMENT
				+ "event E1 { new (id:int) { } }"); //$NON-NLS-1$
		this.helper.getValidator().assertNoErrors(event);
		Object feature = event.getFeatures().get(0);
		assertNotNull(feature);
		assertTrue(feature instanceof Constructor);
		Object text = this.provider.getText(feature);
		assertNotNull(text);
		assertEquals("E1(int)", text); //$NON-NLS-1$
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void getTextAction_noParam_noReturn() throws Exception {
		Agent agent = this.helper.createSARLTopElement(
				filename("textAction0"), //$NON-NLS-1$
				Agent.class,
				PACKAGE_STATEMENT
				+ "agent A1 { def myAction { } }"); //$NON-NLS-1$
		this.helper.getValidator().assertNoErrors(agent);
		Object feature = agent.getFeatures().get(0);
		assertNotNull(feature);
		assertTrue(feature instanceof Action);
		Object text = this.provider.getText(feature);
		assertNotNull(text);
		assertEquals("myAction() : void", text); //$NON-NLS-1$
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void getTextAction_noParam_return() throws Exception {
		Agent agent = this.helper.createSARLTopElement(
				filename("textAction1"), //$NON-NLS-1$
				Agent.class,
				PACKAGE_STATEMENT
				+ "agent A1 { def myAction : int { 0 } }"); //$NON-NLS-1$
		this.helper.getValidator().assertNoErrors(agent);
		Object feature = agent.getFeatures().get(0);
		assertNotNull(feature);
		assertTrue(feature instanceof Action);
		Object text = this.provider.getText(feature);
		assertNotNull(text);
		assertEquals("myAction() : int", text); //$NON-NLS-1$
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void getTextAction_param_noReturn() throws Exception {
		Agent agent = this.helper.createSARLTopElement(
				filename("textAction2"), //$NON-NLS-1$
				Agent.class,
				PACKAGE_STATEMENT
				+ "agent A1 { def myAction(a:char) { } }"); //$NON-NLS-1$
		this.helper.getValidator().assertNoErrors(agent);
		Object feature = agent.getFeatures().get(0);
		assertNotNull(feature);
		assertTrue(feature instanceof Action);
		Object text = this.provider.getText(feature);
		assertNotNull(text);
		assertEquals("myAction(char) : void", text); //$NON-NLS-1$
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void getTextAction_param_return() throws Exception {
		Agent agent = this.helper.createSARLTopElement(
				filename("textAction3"), //$NON-NLS-1$
				Agent.class,
				PACKAGE_STATEMENT
				+ "agent A1 { def myAction(a:char) : int { 0 } }"); //$NON-NLS-1$
		this.helper.getValidator().assertNoErrors(agent);
		Object feature = agent.getFeatures().get(0);
		assertNotNull(feature);
		assertTrue(feature instanceof Action);
		Object text = this.provider.getText(feature);
		assertNotNull(text);
		assertEquals("myAction(char) : int", text); //$NON-NLS-1$
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void getTextActionSignature_noParam_noReturn() throws Exception {
		Capacity capacity = this.helper.createSARLTopElement(
				filename("textActionSignature0"), //$NON-NLS-1$
				Capacity.class,
				PACKAGE_STATEMENT
				+ "capacity C1 { def myAction }"); //$NON-NLS-1$
		this.helper.getValidator().assertNoErrors(capacity);
		Object feature = capacity.getFeatures().get(0);
		assertNotNull(feature);
		assertTrue(feature instanceof ActionSignature);
		Object text = this.provider.getText(feature);
		assertNotNull(text);
		assertEquals("myAction() : void", text); //$NON-NLS-1$
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void getTextActionSignature_noParam_return() throws Exception {
		Capacity capacity = this.helper.createSARLTopElement(
				filename("textActionSignature1"), //$NON-NLS-1$
				Capacity.class,
				PACKAGE_STATEMENT
				+ "capacity C1 { def myAction : int }"); //$NON-NLS-1$
		this.helper.getValidator().assertNoErrors(capacity);
		Object feature = capacity.getFeatures().get(0);
		assertNotNull(feature);
		assertTrue(feature instanceof ActionSignature);
		Object text = this.provider.getText(feature);
		assertNotNull(text);
		assertEquals("myAction() : int", text); //$NON-NLS-1$
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void getTextActionSignature_param_noReturn() throws Exception {
		Capacity capacity = this.helper.createSARLTopElement(
				filename("textActionSignature2"), //$NON-NLS-1$
				Capacity.class,
				PACKAGE_STATEMENT
				+ "capacity C1 { def myAction(a:char) }"); //$NON-NLS-1$
		this.helper.getValidator().assertNoErrors(capacity);
		Object feature = capacity.getFeatures().get(0);
		assertNotNull(feature);
		assertTrue(feature instanceof ActionSignature);
		Object text = this.provider.getText(feature);
		assertNotNull(text);
		assertEquals("myAction(char) : void", text); //$NON-NLS-1$
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void getTextActionSignature_param_return() throws Exception {
		Capacity capacity = this.helper.createSARLTopElement(
				filename("textActionSignature3"), //$NON-NLS-1$
				Capacity.class,
				PACKAGE_STATEMENT
				+ "capacity C1 { def myAction(a:char) : int }"); //$NON-NLS-1$
		this.helper.getValidator().assertNoErrors(capacity);
		Object feature = capacity.getFeatures().get(0);
		assertNotNull(feature);
		assertTrue(feature instanceof ActionSignature);
		Object text = this.provider.getText(feature);
		assertNotNull(text);
		assertEquals("myAction(char) : int", text); //$NON-NLS-1$
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void getTextCapacityUses() throws Exception {
		Agent agent = this.helper.createSARLTopElement(
				filename("textCapacityUses"), //$NON-NLS-1$
				Agent.class, 1,
				PACKAGE_STATEMENT
				+ "capacity C1 { }\n" //$NON-NLS-1$
				+ "agent A1 { uses C1 }"); //$NON-NLS-1$
		this.helper.getValidator().assertNoErrors(agent);
		Object feature = agent.getFeatures().get(0);
		assertNotNull(feature);
		assertTrue(feature instanceof CapacityUses);
		Object text = this.provider.getText(feature);
		assertNotNull(text);
		assertEquals("capacity uses", text); //$NON-NLS-1$
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void getTextRequiredCapacity() throws Exception {
		Agent agent = this.helper.createSARLTopElement(
				filename("textRequiredCapacity"), //$NON-NLS-1$
				Agent.class, 1,
				PACKAGE_STATEMENT
				+ "capacity C1 { }\n" //$NON-NLS-1$
				+ "agent A1 { requires C1 }"); //$NON-NLS-1$
		this.helper.getValidator().assertNoErrors(agent);
		Object feature = agent.getFeatures().get(0);
		assertNotNull(feature);
		assertTrue(feature instanceof RequiredCapacity);
		Object text = this.provider.getText(feature);
		assertNotNull(text);
		assertEquals("required capacities", text); //$NON-NLS-1$
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void getTextBehaviorUnit_0() throws Exception {
		Agent agent = this.helper.createSARLTopElement(
				filename("textBehaviorUnit0"), //$NON-NLS-1$
				Agent.class, 1,
				PACKAGE_STATEMENT
				+ "event E1 { }\n" //$NON-NLS-1$
				+ "agent A1 { on E1 { } }"); //$NON-NLS-1$
		this.helper.getValidator().assertNoErrors(agent);
		Object feature = agent.getFeatures().get(0);
		assertNotNull(feature);
		assertTrue(feature instanceof BehaviorUnit);
		Object text = this.provider.getText(feature);
		assertNotNull(text);
		assertEquals("on E1", text); //$NON-NLS-1$
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void getTextBehaviorUnit_1() throws Exception {
		Agent agent = this.helper.createSARLTopElement(
				filename("textBehaviorUnit1"), //$NON-NLS-1$
				Agent.class, 1,
				PACKAGE_STATEMENT
				+ "event E1 { }\n" //$NON-NLS-1$
				+ "agent A1 { on E1 [ true ] { } }"); //$NON-NLS-1$
		this.helper.getValidator().assertNoErrors(agent);
		Object feature = agent.getFeatures().get(0);
		assertNotNull(feature);
		assertTrue(feature instanceof BehaviorUnit);
		Object text = this.provider.getText(feature);
		assertNotNull(text);
		assertEquals("on E1 [true]", text); //$NON-NLS-1$
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void getTextBehaviorUnit_2() throws Exception {
		Agent agent = this.helper.createSARLTopElement(
				filename("textBehaviorUnit2"), //$NON-NLS-1$
				Agent.class, 1,
				PACKAGE_STATEMENT
				+ "event E1 { }\n" //$NON-NLS-1$
				+ "agent A1 { on E1 [ 3 > 5 ] { } }"); //$NON-NLS-1$
		this.helper.getValidator().assertNoErrors(agent);
		Object feature = agent.getFeatures().get(0);
		assertNotNull(feature);
		assertTrue(feature instanceof BehaviorUnit);
		Object text = this.provider.getText(feature);
		assertNotNull(text);
		assertEquals("on E1 [3 > 5]", text); //$NON-NLS-1$
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void getTextBehaviorUnit_3() throws Exception {
		Agent agent = this.helper.createSARLTopElement(
				filename("textBehaviorUnit3"), //$NON-NLS-1$
				Agent.class, 1,
				PACKAGE_STATEMENT
				+ "event E1 { }\n" //$NON-NLS-1$
				+ "agent A1 { on E1 [ 3 <=5 ] { } }"); //$NON-NLS-1$
		this.helper.getValidator().assertNoErrors(agent);
		Object feature = agent.getFeatures().get(0);
		assertNotNull(feature);
		assertTrue(feature instanceof BehaviorUnit);
		Object text = this.provider.getText(feature);
		assertNotNull(text);
		assertEquals("on E1 [3 <=5]", text); //$NON-NLS-1$
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void getTextBehaviorUnit_4() throws Exception {
		Agent agent = this.helper.createSARLTopElement(
				filename("textBehaviorUnit4"), //$NON-NLS-1$
				Agent.class, 1,
				PACKAGE_STATEMENT
				+ "event E1 { }\n" //$NON-NLS-1$
				+ "agent A1 { on E1 [ 1+2+3+4+5+6+7+8+9+10 < 100 ] { } }"); //$NON-NLS-1$
		this.helper.getValidator().assertNoErrors(agent);
		Object feature = agent.getFeatures().get(0);
		assertNotNull(feature);
		assertTrue(feature instanceof BehaviorUnit);
		Object text = this.provider.getText(feature);
		assertNotNull(text);
		assertEquals("on E1 [1+2+3+4...]", text); //$NON-NLS-1$
	}

}
