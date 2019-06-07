package sef.module8.activity;


/**
 * This class represents a simple representation of an account encapsulating
 * a name 
 * 
 * @author John Doe
 *
 */
public class Account {

	private String accountName;

	private boolean isToShort(final String name){
		return name.length() < 4;
	}

	private boolean isToSimple(final String accountName){
		String numbers = "1234567890";
		String letters = "qwertyuiopasdfghjklzxcvbnm";

		String name = accountName.toLowerCase();

		boolean has_number = true;
		boolean has_letter = true;

		for(int i = 0; i < numbers.length(); ++i){
			if(name.indexOf(numbers.charAt(i)) == -1)
				has_number = false;
		}
		for(int i = 0; i < letters.length(); ++i){
			if(name.indexOf(letters.charAt(i)) == -1)
				has_letter = false;
		}

		return has_number || has_letter;
	}

	/**
	 * Creates an Account object with the specified name.  If the account name
	 * given violates the minimum requirements, then an AccountException is thrown
	 * 
	 * @param accountName
	 * @throws AccountException
	 */
	public  Account(String accountName) throws AccountException{
		if(isToShort(accountName))
			throw new AccountException(AccountException.NAME_TOO_SHORT,accountName);
		if(isToSimple(accountName))
			throw new AccountException(AccountException.NAME_TOO_SIMPLE, accountName);

		   this.accountName = accountName;
	}
	
	
	/**
	 * Returns the account name
	 * 
	 * @return the account name
	 */
	public String getName(){
		return this.accountName;
	}
}


