package org.sagebionetworks.schema.generator.handler.schema03;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.StringWriter;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.sagebionetworks.schema.FORMAT;
import org.sagebionetworks.schema.ObjectSchema;
import org.sagebionetworks.schema.TYPE;

import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDeclaration;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFormatter;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.JType;

public class TypeCreatorHandlerImpl03Test {
	
	JCodeModel codeModel;
	JPackage _package;
	JType type;
	ObjectSchema schema;

	@Before
	public void before() throws JClassAlreadyExistsException,
			ClassNotFoundException {
		codeModel = new JCodeModel();
		_package = codeModel._package("org.sample");
//		sampleClass = codeModel._class("Sample");
		schema = new ObjectSchema();
		schema.setType(TYPE.OBJECT);
		// give it a name
		schema.setName("Sample");
	}
	
	@Test
	public void testCreateNewClass() throws ClassNotFoundException{
		// Add a title and description
		String title = "This is the title";
		String description = "Add a description";
		schema.setTitle(title);
		schema.setDescription(description);
		TypeCreatorHandlerImpl03 handler = new TypeCreatorHandlerImpl03();
		// Create the class
		JType clazz = handler.handelCreateType(_package, schema, codeModel._ref(Object.class), null);
		assertNotNull(clazz);
		// Make sure we can call this twice with the same class
		JType second = handler.handelCreateType(_package, schema, codeModel._ref(Object.class), null);
		assertEquals(clazz, second);
		assertTrue(clazz instanceof JDefinedClass);
		JDefinedClass sampleClass = (JDefinedClass)clazz;
		// Write to a string
		String classString = declareToString(sampleClass);
//		System.out.println(classString);
		assertTrue(classString.indexOf(title) > 0);
		assertTrue(classString.indexOf(description) > 0);
		assertTrue(classString.indexOf(TypeCreatorHandlerImpl03.AUTO_GENERATED_MESSAGE) > 0);
	}
	
	@Test
	public void testStringFormatedDateTime() throws ClassNotFoundException{
		// String formated as date-time
		schema.setType(TYPE.STRING);
		schema.setFormat(FORMAT.DATE_TIME);
		TypeCreatorHandlerImpl03 handler = new TypeCreatorHandlerImpl03();
		// Create the class
		JType clazz = handler.handelCreateType(_package, schema, codeModel._ref(Object.class), null);
		assertNotNull(clazz);
		assertEquals(codeModel._ref(Date.class), clazz);
	}
	
	@Test
	public void testStringFormatedDate() throws ClassNotFoundException{
		// String formated as date
		schema.setType(TYPE.STRING);
		schema.setFormat(FORMAT.DATE);
		TypeCreatorHandlerImpl03 handler = new TypeCreatorHandlerImpl03();
		// Create the class
		JType clazz = handler.handelCreateType(_package, schema, codeModel._ref(Object.class), null);
		assertNotNull(clazz);
		assertEquals(codeModel._ref(Date.class), clazz);
	}
	
	@Test
	public void testStringFormatedTime() throws ClassNotFoundException{
		// String formated as date
		schema.setType(TYPE.STRING);
		schema.setFormat(FORMAT.TIME);
		TypeCreatorHandlerImpl03 handler = new TypeCreatorHandlerImpl03();
		// Create the class
		JType clazz = handler.handelCreateType(_package, schema, codeModel._ref(Object.class), null);
		assertNotNull(clazz);
		assertEquals(codeModel._ref(Date.class), clazz);
	}
	
	@Test
	public void testStringFormatedUTC_MILLISEC() throws ClassNotFoundException{
		// String formated as date
		schema.setType(TYPE.STRING);
		schema.setFormat(FORMAT.UTC_MILLISEC);
		TypeCreatorHandlerImpl03 handler = new TypeCreatorHandlerImpl03();
		// Create the class
		JType clazz = handler.handelCreateType(_package, schema, codeModel._ref(Object.class), null);
		assertNotNull(clazz);
		assertEquals(codeModel._ref(Date.class), clazz);
	}
	
	
	@Test
	public void testIntegerFormatedDateTime() throws ClassNotFoundException{
		// String formated as date-time
		schema.setType(TYPE.INTEGER);
		schema.setFormat(FORMAT.DATE_TIME);
		TypeCreatorHandlerImpl03 handler = new TypeCreatorHandlerImpl03();
		// Create the class
		JType clazz = handler.handelCreateType(_package, schema, codeModel._ref(Object.class), null);
		assertNotNull(clazz);
		assertEquals(codeModel._ref(Date.class), clazz);
	}
	
	@Test
	public void testItegerFormatedDate() throws ClassNotFoundException{
		// String formated as date
		schema.setType(TYPE.INTEGER);
		schema.setFormat(FORMAT.DATE);
		TypeCreatorHandlerImpl03 handler = new TypeCreatorHandlerImpl03();
		// Create the class
		JType clazz = handler.handelCreateType(_package, schema, codeModel._ref(Object.class), null);
		assertNotNull(clazz);
		assertEquals(codeModel._ref(Date.class), clazz);
	}
	
	@Test
	public void testIntegerFormatedTime() throws ClassNotFoundException{
		// String formated as date
		schema.setType(TYPE.STRING);
		schema.setFormat(FORMAT.TIME);
		TypeCreatorHandlerImpl03 handler = new TypeCreatorHandlerImpl03();
		// Create the class
		JType clazz = handler.handelCreateType(_package, schema, codeModel._ref(Object.class), null);
		assertNotNull(clazz);
		assertEquals(codeModel._ref(Date.class), clazz);
	}
	
	@Test
	public void testIntegerFormatedUTC_MILLISEC() throws ClassNotFoundException{
		// String formated as date
		schema.setType(TYPE.STRING);
		schema.setFormat(FORMAT.UTC_MILLISEC);
		TypeCreatorHandlerImpl03 handler = new TypeCreatorHandlerImpl03();
		// Create the class
		JType clazz = handler.handelCreateType(_package, schema, codeModel._ref(Object.class), null);
		assertNotNull(clazz);
		assertEquals(codeModel._ref(Date.class), clazz);
	}
	
	
	/**
	 * Helper to declare a model object to string.
	 * @param toDeclare
	 * @return
	 */
	public String declareToString(JDeclaration toDeclare){
		StringWriter writer = new StringWriter();
		JFormatter formatter = new JFormatter(writer);
		toDeclare.declare(formatter);
		return writer.toString();
	}

}