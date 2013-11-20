/*******************************************************************************
 * Copyright (c) 2000, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.tests.junit;

import junit.framework.*;
import junit.textui.*;

/**
 * Automated Test Suite for class org.eclipse.swt.events.MouseMoveListener
 *
 * @see org.eclipse.swt.events.MouseMoveListener
 */
public class Test_org_eclipse_swt_events_MouseMoveListener extends SwtTestCase {

public Test_org_eclipse_swt_events_MouseMoveListener(String name) {
	super(name);
}

public static void main(String[] args) {
	TestRunner.run(suite());
}

public void test_mouseMoveLorg_eclipse_swt_events_MouseEvent() {
	warnUnimpl("Test test_mouseMoveLorg_eclipse_swt_events_MouseEvent not written");
}


public static Test suite() {
	TestSuite suite = new TestSuite();
	java.util.Vector<String> methodNames = methodNames();
	java.util.Enumeration<String> e = methodNames.elements();
	while (e.hasMoreElements()) {
		suite.addTest(new Test_org_eclipse_swt_events_MouseMoveListener(e.nextElement()));
	}
	return suite;
}

public static java.util.Vector<String> methodNames() {
	java.util.Vector<String> methodNames = new java.util.Vector<String>();
	methodNames.addElement("test_mouseMoveLorg_eclipse_swt_events_MouseEvent");
	return methodNames;
}
@Override
protected void runTest() throws Throwable {
	if (getName().equals("test_mouseMoveLorg_eclipse_swt_events_MouseEvent")) test_mouseMoveLorg_eclipse_swt_events_MouseEvent();
}
}
