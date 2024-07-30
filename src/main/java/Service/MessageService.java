package Service;

import Model.Message;
import DAO.MessageDAO;

/*
 Purpose of this class is to contain business logic between web layer and DAO layer 

 what logic is needed
    create a message 
    cases:
    * can't be an empty message
    * can't be over 255 characters
    * user must exist
    * success
    ----------------------------------------------------------------
    delete a message 
    cases: 
    * must have existing id 
    ----------------------------------------------------------------
    retrieve all messages from a user 
    cases:
    * user must exist
    ----------------------------------------------------------------
    retrieve all messages
    cases: 
    * no message exist in the database
    ----------------------------------------------------------------
    retrieve a message by its id
    cases: 
    * must have existing id
    ----------------------------------------------------------------
    update a message by its id
    cases: 
    * can't be an empty message
    * can't be over 255 characters
    * message_id must exist
    * success
 */
public class MessageService {
    
}
