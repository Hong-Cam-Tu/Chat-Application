package com.chatapplication.usecases.user;

import com.chatapplication.domains.IndividualConversation;
import com.chatapplication.domains.messageimpl.IndividualMessage;
import com.chatapplication.usecases.UseCase;
import com.chatapplication.usecases.adapters.DataStorage;
import com.chatapplication.usecases.adapters.IndividualConversationStorage;

public class GetIdAndNameIndividualConversation 
        extends UseCase<GetIdAndNameIndividualConversation.InputGetIdAndNameIndividualConversation,
        GetIdAndNameIndividualConversation.OutputGetIdAndNameIndividualConversation>{
    private DataStorage _dataStorage;
    private IndividualConversationStorage _individualConversationStorage;

    public GetIdAndNameIndividualConversation(DataStorage dataStorage, IndividualConversationStorage individualConversationStorage) {
        _dataStorage = dataStorage;
        _individualConversationStorage = individualConversationStorage;
    }

    public class InputGetIdAndNameIndividualConversation {
        private String _idConversation;
        private String _idUser;

        InputGetIdAndNameIndividualConversation(String idConversation,String idUser) {
            _idConversation = idConversation;
            _idUser = idUser;
        }
    }

    public enum GetIdAndNameIndividualConversationResult {
        Successed,
        Failed;
     }

    public class OutputGetIdAndNameIndividualConversation {
        private final GetIdAndNameIndividualConversationResult _result;
        private final String _message;
        private final String _idReceiver;
        private final String _nameReceiver;

        OutputGetIdAndNameIndividualConversation(GetIdAndNameIndividualConversationResult result, String message,String idReceiver,String nameReceiver) {
            _result = result;
            _message = message;
            _idReceiver = idReceiver;
            _nameReceiver = nameReceiver;
        }
        
        public String get_idReceiver() {
            return _idReceiver;
        }

        public String get_nameReceiver() {
            return _nameReceiver;
        }

        public GetIdAndNameIndividualConversationResult getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }
    }

    @Override
    public OutputGetIdAndNameIndividualConversation excute(InputGetIdAndNameIndividualConversation input) {
        IndividualConversation conversation = _individualConversationStorage.getConversation().getById(input._idConversation);
        
        if(conversation == null) {
            return new OutputGetIdAndNameIndividualConversation(GetIdAndNameIndividualConversationResult.Failed, "Fail", null, null);
        } else {
            IndividualMessage message = conversation.getMessages().get(0);
            String idReceiver = message.getIdSender().equals(input._idUser) ? message.getIdReceiver() : message.getIdSender();
            String nameReceiver = _dataStorage.getUsers().getById(idReceiver).getFullName();
            return new OutputGetIdAndNameIndividualConversation(GetIdAndNameIndividualConversationResult.Successed, "Successed", idReceiver, nameReceiver);
        }
    }

}
