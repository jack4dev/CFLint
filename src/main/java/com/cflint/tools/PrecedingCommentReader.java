package com.cflint.tools;

import org.antlr.v4.runtime.Token;

import com.cflint.plugins.Context;

import cfml.CFSCRIPTLexer;

public class PrecedingCommentReader {
	
	public static final String CFC_DEFAULT_EXTENSION = ".cfc";
	public static final String CFM_DEFAULT_EXTENSION = ".cfm";

	public static String getMultiLine(Context context, final Token token) {
		Iterable<Token> tokens = context.beforeTokens(token);
		for (Token currentTok : tokens) {
			if (currentTok.getChannel() == Token.HIDDEN_CHANNEL && currentTok.getType() == CFSCRIPTLexer.ML_COMMENT) {
				String mlText = currentTok.getText();
				return mlText == null?null:mlText.trim();
			} else if (currentTok.getLine() < token.getLine()) {
				break;
			}
		}
		return null;
	}
}
