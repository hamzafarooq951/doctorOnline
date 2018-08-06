package webService;




public class AuthenticationEndpoint {
    
	
	public static Boolean authenticateToken(String token, String username) throws Exception {
		
        // Authenticate against a database, LDAP, file or whatever
        // Throw an Exception if the credentials are invalid
    	String realToken = "//" + username + "34%2@" + username.hashCode();
    	
    	if(realToken.matches(token)) {
    		return true;
    	} else {
    		return false;
    	}
    	
    }

    public static String issueToken(String username) {
    	String token = "//" + username + "34%2@" + username.hashCode();
    	System.out.println(token);
		return token;
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
    }
}
