package com.chatApplication.usecases.user;

import com.chatApplication.domains.IndividualConversation;
import com.chatApplication.domains.messageimpl.IndividualMessage;
import com.chatApplication.usecases.UseCase;
import com.chatApplication.usecases.adapters.DataStorage;
import com.chatApplication.usecases.adapters.IndividualConversationStorage;

public class GetIdAndNameIndividualConversation 
        extends UseCase<GetIdAndNameIndividualConversation.InputValues,GetIdAndNameIndividualConversation.OutputValues>{
    private DataStorage _dataStorage;
    private IndividualConversationStorage _individualConversationStorage;

    public GetIdAndNameIndividualConversation(DataStorage dataStorage, IndividualConversationStorage individualConversationStorage) {
        _dataStorage = dataStorage;
        _individualConversationStorage = individualConversationStorage;
    }

    public class InputValues {
        private String _idConversation;
        private String _idUser;

        InputValues(String idConversation,String idUser) {
            _idConversation = idConversation;
            _idUser = idUser;
        }
    }

    public enum Result {
        Successed,
        Failed;
     }

    public class OutputValues {
        private final Result _result;
        private final String _message;
        private final String _idReceiver;
        private final String _nameReceiver;

        OutputValues(Result result, String message,String idReceiver,String nameReceiver) {
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

        public Result getResult(){
            return _result;
        }

        public String getMessage(){
            return _message;
        }
    }

    @Override
    public OutputValues excute(InputValues input) {
        IndividualConversation conversation = _individualConversationStorage.getConversation().getById(input._idConversation);
        
        if(conversation == null) {
            return new OutputValues(Result.Failed, "Fail", null, null);
        } else {
            IndividualMessage message = conversation.getMessages().get(0);
            String idReceiver = message.getIdSender().equals(input._idUser) ? message.getIdReceiver() : message.getIdSender();
            String nameReceiver = _dataStorage.getUsers().getById(idReceiver).getFullName();
            return new OutputValues(Result.Successed, "Successed", idReceiver, nameReceiver);
        }
    }

}
