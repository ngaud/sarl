/*
* generated by Xtext
*/
package io.sarl.lang.parser.antlr;

import java.io.InputStream;
import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

public class SARLAntlrTokenFileProvider implements IAntlrTokenFileProvider {

	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
    	return classLoader.getResourceAsStream("io/sarl/lang/parser/antlr/internal/InternalSARL.tokens");
	}
}
