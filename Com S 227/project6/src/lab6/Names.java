package lab6;

public class Names {

	public static String test(String user) {
		String initials = "";
		char white = ' ';
		for(int i = 0; i < user.length(); i++) {
			if(i == 0) {
				initials += user.charAt(i);
			}
			if(user.charAt(i) == white) {
				initials += user.charAt(i+1);
			}
		}
		return initials;
	}
	public static int vowels(String comp) {
		int index = 0;
		char nun;
		for(int i = 0; i < comp.length(); i++) {
			nun = comp.charAt(i);
			if("aeiouAEIOU".indexOf(nun) >= 0) {
				index = i;
				break;
			}
			else {
				index = -1;
			}
		}
		return index;
	}
}
