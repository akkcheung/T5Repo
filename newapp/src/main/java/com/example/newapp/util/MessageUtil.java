package com.example.newapp.util;

import java.text.MessageFormat;
import java.util.Properties;

public class MessageUtil {
	
	static private String MESSAGE_FILE_NAME = "jumpstart/business/messageDefs.properties";
	static private Properties messageDefs = null;

	static private Properties loadMessageDefs(Object anyObject) {

		if (messageDefs == null) {
			messageDefs = ResourceUtil.getAsProperties(MESSAGE_FILE_NAME);
		}

		return messageDefs;
	}

	static public String toText(String messageId) {
		try {
			Properties p = loadMessageDefs(messageId);

			String s = p.getProperty(messageId);
			if (s != null) {
				return s;
			}
			else {
				return ("[Contact I.T.  MessageUtil cannot find message id \"" + messageId + "\" in "
						+ MESSAGE_FILE_NAME + ".]");
			}
		}
		catch (UtilRuntimeException e) {
			System.err.println("Failed to get message for \"" + messageId + "\".");
			throw e;
		}

	}

	static public String toText(String messageId, Object messageArg) {
		return toText(messageId, new Object[] { messageArg });
	}

	static public String toText(String messageId, Object[] messageArgs) {
		String message = null;
		Properties p = loadMessageDefs(messageId);
		String s = p.getProperty(messageId);

		if (s != null) {
			if (messageArgs == null) {
				message = s;
			}
			else {
				MessageFormat messageFormat = new MessageFormat(s);
				message = messageFormat.format(messageArgs);
			}

			return message;
		}
		else {
			return ("[Contact I.T.  MessageUtil cannot find message id \"" + messageId + "\" in " + MESSAGE_FILE_NAME + ".]");
		}

	}
}
