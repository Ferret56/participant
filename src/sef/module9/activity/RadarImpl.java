package sef.module9.activity;

import java.util.*;

/**
 * Implementation of a Radar 
 * 
 *
 */
public class RadarImpl implements Radar{

		List<RadarContact> contacts;
	
	/**
	 *  Constructs a new Radar 
	 */
	public RadarImpl(){
		contacts = new ArrayList<RadarContact>();
	}
	
	
	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#addContact(sef.module8.activity.RadarContact)
	 */
	public RadarContact addContact(RadarContact contact) {
		if(contact != null){
			int index_find = contacts.indexOf(contact);
			if(index_find != -1 && contacts.get(index_find).getContactID().equals(contact.getContactID()))
				contacts.add(index_find, contact);
			else
				contacts.add(contact);
		}
		return contact;
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#getContact(java.lang.String)
	 */
	public RadarContact getContact(String id) {
		for(RadarContact contact : contacts){
			if(contact.getContactID().equals(id))
				return contact;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#getContactCount()
	 */
	public int getContactCount(){
		return contacts.size();
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#removeContact(java.lang.String)
	 */
	public RadarContact removeContact(String id) {
		for(RadarContact contact : contacts){
			if(contact.getContactID().equals(id)){
				contacts.remove(contact);
				return contact;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#returnContacts()
	 */
	public List<RadarContact> returnContacts() {
		return new ArrayList<RadarContact>(contacts);
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#returnContacts(java.util.Comparator)
	 */
	public List<RadarContact> returnContacts(Comparator<RadarContact> comparator){

	//return Collections.sort(arg0, new DistanceComparator());
		List<RadarContact> tmp = new ArrayList<RadarContact>(contacts);
		Collections.sort(tmp,comparator);
		return tmp;
	}

	
}
