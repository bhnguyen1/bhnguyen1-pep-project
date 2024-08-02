package Service;

import Model.Message;
import DAO.MessageDAO;

import java.util.ArrayList;
import java.util.List;

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
   private MessageDAO messageDAO;

   public MessageService() { //constructor
       messageDAO = new MessageDAO();
   }

   public MessageService(MessageDAO messageDAO) { //constructor for when MessageDao is provided
       this.messageDAO = messageDAO;
   }
   
   //ask if all of these checks should return null
   public Message updateMessage(int message_id, Message newMessage) {
      Message message = messageDAO.getMessage(message_id);
      if(message == null) {
         System.out.println("Message does not exist!");
         return null;
      }
      if(newMessage.getMessage_text().isEmpty() || newMessage.getMessage_text().length() > 255) {
         System.out.println("Invalid Message!");
         return null;
      }
      messageDAO.updateMessage(message_id, newMessage);
      return new Message(message_id, message.getPosted_by(), newMessage.getMessage_text(), message.getTime_posted_epoch());
   }

   //only need them to look if there is more to be added
   public Message retrieveMessage(int message_id) {
      Message message = messageDAO.getMessage(message_id);
      if(message == null) {
         System.out.println("Message does not exist!");
      } 
      return message;
   }

   public List<Message> retrieveAllMessage() {
      return messageDAO.getMessages();
   }

   //look here if logic is wrong for retrieving user message
   public List<Message> retrieveUserMessages(int user_id) {
      if(messageDAO.checkUser(user_id) == false) {
         System.out.println("User does not exist");
         return null;
      } else {
         List<Message> messages = messageDAO.getAllMessagesFromUser(user_id);
         if(messages.isEmpty()) {
            return new ArrayList<>();
         } else {
            return messageDAO.getAllMessagesFromUser(user_id);
         }
      }
   }

   //ask mentor about logic
   public boolean deleteMessage(Message message) {
      if(messageDAO.getMessage(message.getMessage_id()) == null) {
         return false;
      } else {
         messageDAO.deleteMessage(message.getMessage_id());
         return true;
      }
   }

   //look at the logic done here later (base script)
   public Message makeMessage(Message message) {
      if(messageDAO.checkUser(message.getPosted_by()) == false) {
         System.out.println("User does not exist");
         return null;
      }
      if(message.getMessage_text().isEmpty() || message.getMessage_text().length() > 255) {
         System.out.println("Invalid Text");
         return null;
      }
      return messageDAO.insertMessage(message);
   }
}
