/*
 * Copyright (c) 2018 Homelet Wei
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
package homelet.Display.State.Encode.encodeTemplate;

public class TemplateUtility{
	
	private static TemplateUtility templateUtility = new TemplateUtility();
	
	private TemplateUtility(){
	}
	
	public static TemplateUtility getTemplateUtility(){
		return templateUtility;
	}
	
	/**
	 * String filiter string [ ].
	 *
	 * @param buffer         the buffer
	 * @param takeInPosition the take in position
	 * @return the string [ ]
	 */
	public String[] stringFiliter(String[][] buffer, int takeInPosition){
		String[] result = new String[buffer.length];
		int      cursor = 0;
		for(String[] sr : buffer){
			if(sr == null || sr.length == 0)
				continue;
			result[cursor++] = sr[takeInPosition];
		}
		return result;
	}
	
	/**
	 * TODO
	 *
	 * @param str    the str
	 * @param length the length
	 * @return string [ ] [ ]
	 * @author HomeletWei
	 */
	public String[][] packUpStrings(String[] str, int length){
		if(str == null || str.length == 0)
			return null;
		String[][] buffer = new String[(str.length / length) + (str.length % length == 0 ? 0 : 1)][length];
		for(int index = 0; index < str.length; index++){
			buffer[index / length][index % length] = str[index];
		}
		return buffer;
	}
	
	/**
	 * String fillter string.
	 *
	 * @param str the str
	 * @return the string
	 */
	public String stringFillter(String str){
		if(str == null)
			return "";
		String b = str.replaceAll(" ", "");
		return (b.length() == 0 || b.contentEquals("*")) ? "" : str;
	}
}
